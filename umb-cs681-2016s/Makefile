COURSE_NAME_FULL = umb-cs681-2016s

# find the path to directory where this Makefile lives
ROOT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
BIN_DIR = $(ROOT_DIR)/../bin/${COURSE_NAME_FULL}

.PHONY: all code clean dirs

all: dirs code

dirs:
	@mkdir -p $(BIN_DIR)

code:
	@ant -buildfile ${ROOT_DIR}/build.xml init-ivy
	@ant -buildfile ${ROOT_DIR}/build.xml dist

clean:
	@printf "  Removing Build Artifacts... "
	@ant -buildfile ${ROOT_DIR}/build.xml clean >> /dev/null
	@echo "Done."
