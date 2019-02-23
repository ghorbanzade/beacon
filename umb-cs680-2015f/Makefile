COURSE_NAME_FULL = umb-cs680-2015f

# find the path to directory where this Makefile lives
ROOT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
BIN_DIR = $(ROOT_DIR)/../bin/${COURSE_NAME_FULL}
DOC_DIR = ${ROOT_DIR}/doc
OUT_DIR = $(BIN_DIR)/doc
TEX_DIR = $(DOC_DIR)/tex

DOC_TEX = $(TEX_DIR)/main.tex
DOC_PDF = $(BIN_DIR)/${COURSE_NAME_FULL}-main.pdf

.PHONY: all docs bind code tidy clean

all: dirs code docs bind tidy

docs: dirs $(DOC_PDF) tidy

dirs:
	@mkdir -p $(BIN_DIR)

$(DOC_PDF): $(DOC_TEX)
	@printf "  $(@F)... "
	@pdflatex -halt-on-error -output-directory $(BIN_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:${COURSE_NAME_FULL}-%.pdf=%.tex) > /dev/null
	@pdflatex -halt-on-error -output-directory $(BIN_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:${COURSE_NAME_FULL}-%.pdf=%.tex) > /dev/null
	@echo "Done."

bind:
	@printf "  Binding documents... "
	@gs -dBATCH -dNOPAUSE -q -sDEVICE=pdfwrite -sOutputFile=$(BIN_DIR)/$(COURSE_NAME_FULL).pdf $(DOC_PDF) > /dev/null
	@echo "Done."

code:
	@ant -buildfile ${ROOT_DIR}/build.xml init-ivy
	@ant -buildfile ${ROOT_DIR}/build.xml dist

tidy:
	@printf "  Removing unneeded files... "
	@find $(BIN_DIR) -name '*.log' -delete
	@find $(BIN_DIR) -name '*.aux' -delete
	@echo "Done."

clean:
	@printf "  Removing $(BIN_DIR)... "
	@rm -rf $(BIN_DIR)
	@echo "Done."
	@printf "  Removing Build Artifacts... "
	@ant -buildfile ${ROOT_DIR}/build.xml clean >> /dev/null
	@echo "Done."
