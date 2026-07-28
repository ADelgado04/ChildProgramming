import { NextResponse } from "next/server"
import type { Round } from "../../../../lib/types"

export async function GET() {
  try {
    const response = await fetch("http://localhost:8080/api/round/list")
    if (!response.ok) throw new Error(`Error ${response.status}`)
    const data: Round[] = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error("[ERROR] Al obtener rondas:", error)
    return NextResponse.json({ error: "Error al obtener rondas" }, { status: 500 })
  }
}
