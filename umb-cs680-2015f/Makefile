COURSE_NAME_FULL = umb-cs680-2015f

# find the path to directory where this Makefile lives
ROOT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
BUILD_DIR =$(shell dirname $(ROOT_DIR))/build/$(COURSE_NAME_FULL)
DOC_DIR = $(BUILD_DIR)/doc
LOG_DIR = $(BUILD_DIR)/log
TEX_DIR = $(ROOT_DIR)/doc/tex

DOC_TEX = $(TEX_DIR)/main.tex
DOC_PDF = $(DOC_DIR)/${COURSE_NAME_FULL}-main.pdf

.PHONY: all docs bind code tidy clean

all: dirs code docs bind tidy

docs: dirs $(DOC_PDF) tidy

dirs:
	@mkdir -p $(DOC_DIR)
	@mkdir -p $(LOG_DIR)

$(DOC_PDF): $(DOC_TEX)
	@printf "  $(@F)... "
	@pdflatex -halt-on-error -output-directory $(DOC_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:$(COURSE_NAME_FULL)-%.pdf=%.tex) &> $(LOG_DIR)/$(@F:.pdf=.tex.log)
	@pdflatex -halt-on-error -output-directory $(DOC_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:${COURSE_NAME_FULL}-%.pdf=%.tex) > /dev/null
	@echo "Done."

bind:
	@printf "  Binding documents... "
	@gs -dBATCH -dNOPAUSE -q -sDEVICE=pdfwrite -sOutputFile=$(DOC_DIR)/$(COURSE_NAME_FULL).pdf $(DOC_PDF) > /dev/null
	@echo "Done."

code:
	@printf "  Building Source Code... "
	@ant -buildfile ${ROOT_DIR}/build.xml init-ivy &> $(LOG_DIR)/$(COURSE_NAME_FULL).ant.log
	@ant -buildfile ${ROOT_DIR}/build.xml dist &> $(LOG_DIR)/$(COURSE_NAME_FULL).ant.log
	@echo "Done."

tidy:
	@printf "  Removing unneeded files... "
	@find $(DOC_DIR) -name '*.log' -delete
	@find $(DOC_DIR) -name '*.aux' -delete
	@echo "Done."

clean:
	@printf "  Removing $(BUILD_DIR)... "
	@rm -rf $(BUILD_DIR)
	@echo "Done."