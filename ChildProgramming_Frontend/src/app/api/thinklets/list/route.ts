import { NextResponse } from "next/server"
import type { Thinklet } from "../../../../lib/types"

export async function GET() {
  try {
    const response = await fetch("http://localhost:8080/api/thinklet/list")
    if (!response.ok) throw new Error(`Error ${response.status}`)
    const data: Thinklet[] = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error("[ERROR] Al obtener las practicas:", error)
    return NextResponse.json({ error: "Error al obtener las practicas" }, { status: 500 })
  }
}
