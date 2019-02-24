#!/usr/bin/env bash

show_usage () {
    cat << EOF
usage: $(basename "$0") [ -h | --long-options]
  -h, --help              shows this message
  -a, --all               builds all courses

  --umb-cs622-2015f       builds Theory of Formal Languages
  --umb-cs624-2015s       builds Analysis of Algorithms
  --umb-cs630-2014f       builds Database Management Systems
  --umb-cs637-2015s       builds Database Backed Websites
  --umb-cs638-2016s       builds Applied Machine Learning
  --umb-cs671-2015s       builds Machine Learning
  --umb-cs680-2015f       builds Object-Oriented Design and Programming
  --umb-cs681-2016s       builds Object Oriented Software Development
EOF
}

# configure bash environment
set -o errexit -o pipefail -o noclobber -o nounset

# declare project structure

DIR_PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# initialize logging

__log () {
    if [ $# -lt 3 ]; then return 1; fi
    printf "\e[1;$2m%-10s\e[m %s\\n" "$1" "${@:3}"
}
log_debug () { [[ "${ARG_VERBOSE}" -ne 1 ]] || __log 'debug' '0' "$@"; }
log_info  () { __log 'info' '34' "$@"; }
log_warning () { __log 'warn' '33' "$@"; }
log_error () { __log 'error' '31' "$@"; return 1; }

# start build process

ARG_HELP=0

declare -A COMPONENTS=(
    ["umb-cs622-2015f"]=0
    ["umb-cs624-2015s"]=0
    ["umb-cs630-2014f"]=0
    ["umb-cs637-2015s"]=0
    ["umb-cs638-2016s"]=0
    ["umb-cs671-2015s"]=0
    ["umb-cs680-2015f"]=0
    ["umb-cs681-2016s"]=0
)

for arg in "$@"; do
    case $arg in
        "-h" | "help" | "--help")
            ARG_HELP=1
            ;;
        "-a" | "--all")
            for K in "${!COMPONENTS[@]}"; do COMPONENTS[$K]=1; done
            ;;
    esac
done

if [[ ${ARG_HELP} -eq 1 ]]; then
    show_usage
    exit
fi

for K in "${!COMPONENTS[@]}"; do
    if [ "${COMPONENTS[$K]}" -eq 0 ]; then continue; fi
    log_info "Building ${K}"
    make -C "${DIR_PROJECT_ROOT}/${K}"
done
