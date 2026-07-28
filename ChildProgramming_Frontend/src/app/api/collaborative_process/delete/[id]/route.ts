import { NextResponse } from "next/server"

export async function DELETE(request: Request) {
  try {
    const { id } = await request.json()

    if (!id) {
      return NextResponse.json({ error: "Debe especificar un ID" }, { status: 400 })
    }

    const response = await fetch(`http://localhost:8080/api/colaborative_process/delete/${id}`, {
      method: "DELETE",
    })

    if (!response.ok) {
      throw new Error(`Error ${response.status} al eliminar proceso`)
    }

    return NextResponse.json({ success: true, id })
  } catch (error) {
    console.error("[ERROR] Al eliminar proceso:", error)
    return NextResponse.json({ error: "Error al eliminar el proceso" }, { status: 500 })
  }
}
