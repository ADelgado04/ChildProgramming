
import type { Thinklet } from "../types"

const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080"

export async function getPatterns(): Promise<Thinklet[]> {
  const response = await fetch(`${API_BASE_URL}/api/thinklet/list`)
  if (!response.ok) throw new Error("Failed to fetch thinklet")
  return response.json()
}

export async function getPatternById(id: string): Promise<Thinklet> {
  const response = await fetch(`${API_BASE_URL}/api/thinklet/find/${id}`)
  if (!response.ok) throw new Error("Failed to fetch thinklet")
  return response.json()
}

export async function createPattern(data: Omit<Thinklet, "id">): Promise<Thinklet> {
  const response = await fetch(`${API_BASE_URL}/api/thinklet/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to create thinklet")
  return response.json()
}

export async function updatePattern(id: string, data: Partial<Thinklet>): Promise<Thinklet> {
  const response = await fetch(`${API_BASE_URL}/api/thinklet/update/${id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to update thinklet")
  return response.json()
}

export async function deletePattern(id: number): Promise<void> {
  const response = await fetch(`${API_BASE_URL}/api/thinklet/delete/${id}`, {
    method: "DELETE",
  })
  if (!response.ok) throw new Error("Failed to delete Thinklet")
}
