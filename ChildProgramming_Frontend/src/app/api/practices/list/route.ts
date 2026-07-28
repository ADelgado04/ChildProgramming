import { NextResponse } from "next/server"
import type { Practice } from "../../../../lib/types"

export async function GET() {
  try {
    const response = await fetch("http://localhost:8080/api/practice/list")
    if (!response.ok) throw new Error(`Error ${response.status}`)
    const data: Practice[] = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error("[ERROR] Al obtener las practicas:", error)
    return NextResponse.json({ error: "Error al obtener las practicas" }, { status: 500 })
  }
}
