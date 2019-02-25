COURSE_NAME_FULL = umb-cs681-2016s

# find the path to directory where this Makefile lives
ROOT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
BUILD_DIR =$(shell dirname $(ROOT_DIR))/build/$(COURSE_NAME_FULL)
LOG_DIR = $(BUILD_DIR)/log

.PHONY: all code clean dirs

all: dirs code

dirs:
	@mkdir -p $(LOG_DIR)

code:
	@printf "  Building Source Code... "
	@ant -buildfile ${ROOT_DIR}/build.xml init-ivy &> $(LOG_DIR)/$(COURSE_NAME_FULL).ant.log
	@ant -buildfile ${ROOT_DIR}/build.xml dist &> $(LOG_DIR)/$(COURSE_NAME_FULL).ant.log
	@echo "Done."

clean:
	@printf "  Removing $(BUILD_DIR)... "
	@rm -rf $(BUILD_DIR)
	@echo "Done."
