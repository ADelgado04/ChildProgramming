export interface Process {
  id: number
  name: string
  goal: string
}

export interface Activity {
  id: number
  name: string
  description: string
  processId: number
}

export interface Role {
  id: number
  name: string
  description: string
}

export interface Round {
  id: number
  name: string
  order: number
  processId: number
}
