#!/usr/bin/env bash

show_usage () {
    cat << EOF
usage: $(basename "$0") [ -h | --long-options]
  --help                shows this message

  build modes:
    --native            use local build toolchain
    --docker            use docker as the build toolchain [default]

  build options:
    --clear             removes build artifacts

  components:
    --all               builds all courses
    umb-cs622-2015f     builds Theory of Formal Languages
    umb-cs624-2015s     builds Analysis of Algorithms
    umb-cs630-2014f     builds Database Management Systems
    umb-cs637-2015s     builds Database Backed Websites
    umb-cs638-2016s     builds Applied Machine Learning
    umb-cs671-2015s     builds Machine Learning
    umb-cs680-2015f     builds Object-Oriented Design and Programming
    umb-cs681-2016s     builds Object Oriented Software Development
EOF
}

# configure bash environment
set -o errexit -o pipefail -o noclobber -o nounset

# declare project structure

ARG_VERBOSE=0
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

# this script expects bash v4.4 or higher

if [ "${BASH_VERSINFO[0]}" -lt 4 ] || { [ "${BASH_VERSINFO[0]}" -eq 4 ] && \
    [ "${BASH_VERSINFO[1]}" -lt 4 ]; }; then
    log_warning "you are using bash version ${BASH_VERSION}"
    log_error "this script requires bash version 4.4 or higher"
fi

# helper functions

count_keys_if_set () {
    if [ $# -ne 1 ]; then return 1; fi
    local sum=0
    declare -A arr=${1#*=}
    for k in "${!arr[@]}"; do
        if [ "${arr[$k]}" -eq 1 ]; then
            sum=$((sum + 1))
        fi
    done
    echo $sum
}

# more specific helper functions

validate_arguments () {
    if [ $# -ne 3 ]; then return 1; fi
    declare -A modes=${1#*=}
    declare -A components=${3#*=}
    local count_modes
    local count_components
    count_modes="$(count_keys_if_set "$(declare -p modes)")"
    count_components="$(count_keys_if_set "$(declare -p components)")"
    if [ "$count_modes" -lt 1 ]; then
        log_error "specify a build mode"
    fi
    if [ "$count_modes" -gt 1 ]; then
        log_error "only one build mode can be specified"
    fi
    if [ "$count_components" -eq 0 ]; then
        log_error "specify at least one component"
    fi
}

build_components () {
    if [ $# -ne 3 ]; then return 1; fi
    declare -A modes=${1#*=}
    declare -A opts=${2#*=}
    declare -A components=${3#*=}

    if [ "${#components[@]}" -ne 0 ]; then
        log_debug "components:"
        for J in "${!components[@]}"; do
            if [ "${components[$J]}" -eq 1 ]; then
                log_debug " - $J"
            fi
        done
    fi

    if [ "${modes["native"]}" -eq 1 ]; then
        if [ "${opts["clear"]}" -eq 1 ]; then
            log_info "removing local artifacts"
            for K in "${!components[@]}"; do
                if [ "${components[$K]}" -eq 0 ]; then continue; fi
                make -C "${DIR_PROJECT_ROOT}/${K}" clean
            done
            log_info "removed local artifacts"
        else
            log_info "using local build toolchain"
            for K in "${!components[@]}"; do
                if [ "${components[$K]}" -eq 0 ]; then continue; fi
                log_info "building ${K}"
                make -C "${DIR_PROJECT_ROOT}/${K}"
                log_debug "built ${K}"
            done
        fi
    elif [ "${modes["docker"]}" -eq 1 ]; then
        if [ "${opts["clear"]}" -eq 1 ]; then
            log_info "removing docker artifacts"
            for K in "${!components[@]}"; do
                if [ "${components[$K]}" -eq 0 ]; then continue; fi
                docker rmi "ghorbanzade/${K}"
            done
            log_info "removed docker artifacts"
        else
            log_info "using docker build toolchain"
            for K in "${!components[@]}"; do
                if [ "${components[$K]}" -eq 0 ]; then continue; fi
                log_info "building ${K}"
                docker build -t "ghorbanzade/${K}" -f "${K}/Dockerfile" "${K}"
                log_debug "built ${K}"
            done
        fi
    fi
}

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

declare -A BUILD_MODES=(
    ["docker"]=0
    ["native"]=0
)

declare -A BUILD_OPTS=(
    ["clear"]=0
)

for arg in "$@"; do
    case $arg in
        "-h" | "help" | "--help")
            ARG_HELP=1
            ;;
        "--all")
            for K in "${!COMPONENTS[@]}"; do COMPONENTS[$K]=1; done
            ;;
        "--clear")
            BUILD_OPTS["clear"]=1
            ;;
        "--docker")
            BUILD_MODES["docker"]=1
            ;;
        "--native")
            BUILD_MODES["native"]=1
            ;;
    esac
    for K in "${!COMPONENTS[@]}"; do
        if [ "$arg" == "$K" ]; then
            COMPONENTS["$arg"]=1;
        fi
    done
done

if [[ ${ARG_HELP} -eq 1 ]]; then
    show_usage
    exit
fi

validate_arguments \
    "$(declare -p BUILD_MODES)" \
    "$(declare -p BUILD_OPTS)" \
    "$(declare -p COMPONENTS)"

build_components \
    "$(declare -p BUILD_MODES)" \
    "$(declare -p BUILD_OPTS)" \
    "$(declare -p COMPONENTS)"