import React from "react";
import { useEffect, useState } from "react";

export default function ResponsiveRender({ children }) {
  const [width, setWidth] = useState(() => window.innerWidth);
  useEffect(() => {
    const onResize = () => setWidth(window.innerWidth);
    window.addEventListener("resize", onResize);
    return () => window.removeEventListener("resize", onResize);
  }, []);
  return children({ width, isMobile: width < 576, isTablet: width >= 576 && width < 992, isDesktop: width >= 992 });
}
