import type { ReactNode, HTMLAttributes } from "react";

type ScrollAreaProps = HTMLAttributes<HTMLDivElement> & {
  children: ReactNode;
  className?: string;
};

export function ScrollArea({ children, className = "", ...props }: ScrollAreaProps) {
  return (
    <div
      className={`overflow-y-auto max-h-96 border border-blue-300 rounded-md p-2 ${className}`}
      {...props}
    >
      {children}
    </div>
  );
}
