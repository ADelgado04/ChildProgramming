import type { Practice } from "../types"

const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080"

export async function getPractices(): Promise<Practice[]> {
  const response = await fetch(`${API_BASE_URL}/api/practice/list`)
  if (!response.ok) throw new Error("Failed to fetch activities")
  return response.json()
}

export async function getPractice(id: string): Promise<Practice> {
  const response = await fetch(`${API_BASE_URL}/api/practice/find/${id}`)
  if (!response.ok) throw new Error("Failed to fetch Practice")
  return response.json()
}

export async function createParctice(data: Omit<Practice, "id">): Promise<Practice> {
  const response = await fetch(`${API_BASE_URL}/api/practice/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to create Practice")
  return response.json()
}

export async function updatePractice(id: string, data: Partial<Practice>): Promise<Practice> {
  const response = await fetch(`${API_BASE_URL}/api/practice/update/${id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to update Practice")
  return response.json()
}

export async function deletePractice(id: number): Promise<void> {
  const response = await fetch(`${API_BASE_URL}/api/practice/delete/${id}`, {
    method: "DELETE",
  })
  if (!response.ok) throw new Error("Failed to delete Practice")
}
