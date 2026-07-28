
import type { CollaborativePattern } from "../types"

const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080"

export async function getPatterns(): Promise<CollaborativePattern[]> {
  const response = await fetch(`${API_BASE_URL}/api/pattern/list`)
  if (!response.ok) throw new Error("Failed to fetch activities")
  return response.json()
}

export async function getPatternById(id: string): Promise<CollaborativePattern> {
  const response = await fetch(`${API_BASE_URL}/api/pattern/find/${id}`)
  if (!response.ok) throw new Error("Failed to fetch patterns")
  return response.json()
}

export async function createPattern(data: Omit<CollaborativePattern, "id">): Promise<CollaborativePattern> {
  const response = await fetch(`${API_BASE_URL}/api/pattern/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to create pattern")
  return response.json()
}

export async function updatePattern(id: string, data: Partial<CollaborativePattern>): Promise<CollaborativePattern> {
  const response = await fetch(`${API_BASE_URL}/api/pattern/update/${id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to update pattern")
  return response.json()
}

export async function deletePattern(id: number): Promise<void> {
  const response = await fetch(`${API_BASE_URL}/api/pattern/delete/${id}`, {
    method: "DELETE",
  })
  if (!response.ok) throw new Error("Failed to delete pattern")
}
