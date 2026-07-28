import { NextResponse } from "next/server"
import type { Activity } from "../../../../lib/types"

const API_URL = "http://localhost:8080/api/child_activity/list"

export async function GET() {
  try {
    const response = await fetch(API_URL, {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    })

    if (!response.ok) {
      throw new Error(`Error ${response.status} al obtener actividades`)
    }

    const data: Activity[] = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error("[ERROR] Al obtener actividades:", error)
    return NextResponse.json({ error: "Error al obtener actividades" }, { status: 500 })
  }
}
