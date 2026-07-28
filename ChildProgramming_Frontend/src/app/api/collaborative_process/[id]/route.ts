import { NextResponse } from "next/server"

const API_URL = "http://localhost:8080/api/colaborative_process"

export async function GET(request: Request, context: { params: Promise<{ id: string }> }) {
  const { id } = await context.params

  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    })

    if (!response.ok) {
      throw new Error(`Error ${response.status} al obtener el proceso ${id}`)
    }

    const data = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error(`[ERROR] al obtener proceso ${id}:`, error)
    return NextResponse.json({ error: "Error al obtener el proceso" }, { status: 500 })
  }
}

export async function DELETE(request: Request, context: { params: Promise<{ id: string }> }) {
  const { id } = await context.params

  try {
    const response = await fetch(`${API_URL}/delete/${id}`, {
      method: "DELETE",
    })

    if (!response.ok) {
      throw new Error(`Error ${response.status} al eliminar el proceso ${id}`)
    }

    return NextResponse.json({ message: `Proceso ${id} eliminado correctamente` })
  } catch (error) {
    console.error(`[ERROR] al eliminar proceso ${id}:`, error)
    return NextResponse.json({ error: "Error al eliminar el proceso" }, { status: 500 })
  }
}

export async function PUT(request: Request, context: { params: Promise<{ id: string }> }) {
  const { id } = await context.params
  const body = await request.json()

  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    })

    if (!response.ok) {
      throw new Error(`Error ${response.status} al actualizar el proceso ${id}`)
    }

    const data = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error(`[ERROR] al actualizar proceso ${id}:`, error)
    return NextResponse.json({ error: "Error al actualizar el proceso" }, { status: 500 })
  }
}
