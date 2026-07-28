import type { ButtonHTMLAttributes, ReactNode } from "react";

type ButtonProps = ButtonHTMLAttributes<HTMLButtonElement> & {
  children: ReactNode;
  variant?: "primary" | "secondary" | "outline" | "ghost";
  size?: "sm" | "md" | "lg";
  className?: string;
};

export function Button({
  children,
  variant = "primary",
  size = "md",
  className = "",
  ...props
}: ButtonProps) {
  let base =
    "rounded-md font-medium transition focus:outline-none focus:ring-2";
  let variantStyles = "";
  let sizeStyles = "";

  // variantes
  switch (variant) {
    case "primary":
      variantStyles =
        "bg-blue-600 text-white hover:bg-blue-700 focus:ring-blue-400";
      break;
    case "secondary":
      variantStyles =
        "bg-blue-100 text-blue-700 hover:bg-blue-200 focus:ring-blue-300";
      break;
    case "outline":
      variantStyles =
        "border border-blue-500 text-blue-600 hover:bg-blue-50 focus:ring-blue-300";
      break;
    case "ghost":
      variantStyles =
        "text-blue-600 hover:bg-blue-50 focus:ring-blue-300";
      break;
  }

  // tamaños
  switch (size) {
    case "sm":
      sizeStyles = "px-2 py-1 text-sm";
      break;
    case "md":
      sizeStyles = "px-4 py-2 text-base";
      break;
    case "lg":
      sizeStyles = "px-6 py-3 text-lg";
      break;
  }

  return (
    <button
      className={`${base} ${variantStyles} ${sizeStyles} ${className}`}
      {...props}
    >
      {children}
    </button>
  );
}
