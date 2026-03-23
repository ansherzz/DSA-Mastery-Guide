#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"

while IFS= read -r -d '' file; do
  echo "Compiling: ${file#$ROOT_DIR/}"
  javac "$file"
done < <(find "$ROOT_DIR" -name "*.java" -print0)

echo "All Java files compiled successfully."
