import { NextResponse } from "next/server"
import type { CollaborativePattern } from "../../../../lib/types"

export async function GET() {
  try {
    const response = await fetch("http://localhost:8080/api/pattern/list")
    if (!response.ok) throw new Error(`Error ${response.status}`)
    const data: CollaborativePattern[] = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error("[ERROR] Al obtener los patrones:", error)
    return NextResponse.json({ error: "Error al obtener los patrones" }, { status: 500 })
  }
}
