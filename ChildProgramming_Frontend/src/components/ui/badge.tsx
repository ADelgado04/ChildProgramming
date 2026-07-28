import type { ReactNode, HTMLAttributes } from "react";

type BadgeProps = HTMLAttributes<HTMLSpanElement> & {
  children: ReactNode;
  variant?: "primary" | "secondary" | "outline";
  className?: string;
};

export function Badge({
  children,
  variant = "primary",
  className = "",
  ...props
}: BadgeProps) {
  let base = "px-2 py-1 text-sm font-medium rounded";
  let styles = "";

  switch (variant) {
    case "primary":
      styles = "bg-blue-100 text-blue-700";
      break;
    case "secondary":
      styles = "bg-blue-200 text-blue-800";
      break;
    case "outline":
      styles = "border border-blue-400 text-blue-600";
      break;
  }

  return (
    <span className={`${base} ${styles} ${className}`} {...props}>
      {children}
    </span>
  );
}
