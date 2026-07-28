import type { Round } from "../types"

const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080"

export async function getRounds(): Promise<Round[]> {
  const response = await fetch(`${API_BASE_URL}/api/round/list`)
  if (!response.ok) throw new Error("Failed to fetch rounds")
  return response.json()
}

export async function getRound(id: string): Promise<Round> {
  const response = await fetch(`${API_BASE_URL}/api/round/find/${id}`)
  if (!response.ok) throw new Error("Failed to fetch round")
  return response.json()
}

export async function createRound(data: Omit<Round, "id">): Promise<Round> {
  const response = await fetch(`${API_BASE_URL}/api/round/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to create round")
  return response.json()
}

export async function updateRound(id: string, data: Partial<Round>): Promise<Round> {
  const response = await fetch(`${API_BASE_URL}/api/round/update/${id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to update round")
  return response.json()
}

export async function deleteRound(id: number): Promise<void> {
  const response = await fetch(`${API_BASE_URL}/api/round/delete/${id}`, {
    method: "DELETE",
  })
  if (!response.ok) throw new Error("Failed to delete round")
}
