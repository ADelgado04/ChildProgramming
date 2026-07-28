import { NextResponse } from "next/server"
import type { Role } from "../../../../lib/types"

export async function GET() {
  try {
    const response = await fetch("http://localhost:8080/api/role/list")
    if (!response.ok) throw new Error(`Error ${response.status}`)
    const data: Role[] = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error("[ERROR] Al obtener roles:", error)
    return NextResponse.json({ error: "Error al obtener roles" }, { status: 500 })
  }
}
