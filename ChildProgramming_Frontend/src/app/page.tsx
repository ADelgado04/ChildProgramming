"use client"
import Link from "next/link"
import { Search, Plus } from "lucide-react"
import { Process_list } from "@/components/process/process_list"
import Image from "next/image"
import { useState } from "react"
import "./global.css"

export default function HomePage() {
  const [searchTerm, setSearchTerm] = useState("")

  return (
    <div className="container">
      <header className="header">
        {/* Contenedor centrado para logo e título */}
        <div className="flex flex-col items-center justify-center text-center py-8">
          {/* Imagen centrada */}
          <div className="heroImageContainer">
            <Image
              src="/grouplogin.svg"
              alt="Child Programming Logo"
              width={350}
              height={250}
              className="heroImage"
              priority
            />
          </div>

          {/* Título debajo */}
          <h1 className="heroTitle mt-4">Procesos Colaborativos</h1>
        </div>
      </header>

      <main className="main">
        <div className="actionBar">
          <div className="searchContainer">
            <Search className="searchIcon" />
            <input
              type="text"
              placeholder="Buscar procesos..."
              className="searchInput"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)} // ✅ capturamos texto
            />
          </div>

          <Link href="/process/new" className="createButton">
            <Plus className="createButtonIcon" />
            Crear Proceso
          </Link>
        </div>

        {/* pasamos la búsqueda como prop */}
        <Process_list searchTerm={searchTerm} />
      </main>
    </div>
  )
}
