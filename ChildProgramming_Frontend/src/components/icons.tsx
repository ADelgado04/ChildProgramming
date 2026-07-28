export function DashboardIcon({ className }: { className?: string }) {
  return (
    <svg
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      className={className}
    >
      <rect x="3" y="3" width="8" height="8" rx="2" fill="currentColor" />
      <rect x="13" y="3" width="8" height="8" rx="2" fill="currentColor" />
      <rect x="3" y="13" width="8" height="8" rx="2" fill="currentColor" />
      <rect x="13" y="13" width="8" height="8" rx="2" fill="currentColor" />
    </svg>
  )
}

export function CatIcon({ className }: { className?: string }) {
  return (
    <svg
      width="32"
      height="32"
      viewBox="0 0 120 120"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      className={className}
    >
      <ellipse cx="60" cy="70" rx="35" ry="30" fill="currentColor" />
      <circle cx="60" cy="40" r="25" fill="currentColor" />
      <path d="M 45 25 L 35 10 L 50 30 Z" fill="currentColor" />
      <path d="M 45 25 L 38 15 L 48 28 Z" fill="#FFE66D" />
      <path d="M 75 25 L 85 10 L 70 30 Z" fill="currentColor" />
      <path d="M 75 25 L 82 15 L 72 28 Z" fill="#FFE66D" />
      <ellipse cx="50" cy="38" rx="4" ry="6" fill="#2C3E50" />
      <circle cx="50" cy="36" r="1.5" fill="white" />
      <ellipse cx="70" cy="38" rx="4" ry="6" fill="#2C3E50" />
      <circle cx="70" cy="36" r="1.5" fill="white" />
      <path d="M 60 45 L 57 48 L 63 48 Z" fill="#4ECDC4" />
      <path d="M 60 48 Q 55 52 50 50" stroke="#2C3E50" strokeWidth="1.5" fill="none" />
      <path d="M 60 48 Q 65 52 70 50" stroke="#2C3E50" strokeWidth="1.5" fill="none" />
      <line x1="35" y1="42" x2="48" y2="42" stroke="#2C3E50" strokeWidth="1" />
      <line x1="35" y1="46" x2="48" y2="44" stroke="#2C3E50" strokeWidth="1" />
      <line x1="85" y1="42" x2="72" y2="42" stroke="#2C3E50" strokeWidth="1" />
      <line x1="85" y1="46" x2="72" y2="44" stroke="#2C3E50" strokeWidth="1" />
      <ellipse cx="45" cy="95" rx="8" ry="6" fill="#FFA07A" />
      <ellipse cx="75" cy="95" rx="8" ry="6" fill="#FFA07A" />
      <path d="M 90 75 Q 105 70 110 85" stroke="currentColor" strokeWidth="8" fill="none" strokeLinecap="round" />
    </svg>
  )
}
