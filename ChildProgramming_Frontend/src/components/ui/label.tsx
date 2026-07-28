import type { LabelHTMLAttributes, ReactNode } from "react";

type LabelProps = LabelHTMLAttributes<HTMLLabelElement> & {
  children: ReactNode;
  className?: string;
};

export function Label({ children, className = "", ...props }: LabelProps) {
  return (
    <label
      className={`block mb-1 text-blue-700 font-semibold ${className}`}
      {...props}
    >
      {children}
    </label>
  );
}
