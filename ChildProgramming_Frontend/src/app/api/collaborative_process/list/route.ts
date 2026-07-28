import { NextResponse } from "next/server"

export async function GET() {
  try {
    const response = await fetch("http://localhost:8080/api/colaborative_process/list", {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    })

    if (!response.ok) {
      throw new Error(`Error ${response.status} al obtener procesos`)
    }

    const data = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error("[ERROR] Al obtener lista de procesos:", error)
    return NextResponse.json({ error: "Error al obtener los procesos" }, { status: 500 })
  }
}
