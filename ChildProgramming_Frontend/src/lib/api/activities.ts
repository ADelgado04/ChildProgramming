import type { Activity } from "../types"

const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080"

export async function getActivities(): Promise<Activity[]> {
  const response = await fetch(`${API_BASE_URL}/api/child_activity/list`)
  if (!response.ok) throw new Error("Failed to fetch activities")
  return response.json()
}

export async function getActivity(id: string): Promise<Activity> {
  const response = await fetch(`${API_BASE_URL}/api/child_activity/find/${id}`)
  if (!response.ok) throw new Error("Failed to fetch activity")
  return response.json()
}

export async function createActivity(data: Omit<Activity, "id">): Promise<Activity> {
  const response = await fetch(`${API_BASE_URL}/api/child_activity/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to create activity")
  return response.json()
}

export async function updateActivity(id: string, data: Partial<Activity>): Promise<Activity> {
  const response = await fetch(`${API_BASE_URL}/api/child_activity/update/${id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
  if (!response.ok) throw new Error("Failed to update activity")
  return response.json()
}

export async function deleteActivity(id: number): Promise<void> {
  const response = await fetch(`${API_BASE_URL}/api/child_activity/delete/${id}`, {
    method: "DELETE",
  })
  if (!response.ok) throw new Error("Failed to delete activity")
}
