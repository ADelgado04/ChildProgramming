import type { Role } from "../types"

const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080"

export async function getRoles(): Promise<Role[]> {
  const response = await fetch(`${API_BASE_URL}/api/role/list`)
  if (!response.ok) throw new Error("Failed to fetch roles")
  return response.json()
}

export async function getRole(id: string): Promise<Role> {
  const response = await fetch(`${API_BASE_URL}/api/role/find/${id}`)
  if (!response.ok) throw new Error("Failed to fetch role")
  return response.json()
}

export async function createRole(data: Omit<Role, "id">): Promise<Role> {
  const response = await fetch(`${API_BASE_URL}/api/role/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to create role")
  return response.json()
}

export async function updateRole(id: string, data: Partial<Role>): Promise<Role> {
  const response = await fetch(`${API_BASE_URL}/api/role/update/${id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to update role")
  return response.json()
}

export async function deleteRole(id: number): Promise<void> {
  const response = await fetch(`${API_BASE_URL}/api/role/delete/${id}`, {
    method: "DELETE",
  })
  if (!response.ok) throw new Error("Failed to delete role")
}
