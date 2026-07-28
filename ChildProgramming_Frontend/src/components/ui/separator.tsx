import type { HTMLAttributes } from "react";

type SeparatorProps = HTMLAttributes<HTMLDivElement> & {
  orientation?: "horizontal" | "vertical";
  className?: string;
};

export function Separator({
  orientation = "horizontal",
  className = "",
  ...props
}: SeparatorProps) {
  return (
    <div
      role="separator"
      className={`${
        orientation === "horizontal"
          ? "w-full h-px my-2 bg-blue-300"
          : "h-full w-px mx-2 bg-blue-300"
      } ${className}`}
      {...props}
    />
  );
}
