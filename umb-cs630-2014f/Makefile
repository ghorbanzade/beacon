DOCS = hw01 hw02 hw03 hw04 hw05 hw06 m01 f01
COURSE_NAME_FULL = umb-cs630-2014f

# find the path to directory where this Makefile lives
ROOT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
BIN_DIR = $(ROOT_DIR)/../bin/${COURSE_NAME_FULL}
SRC_DIR = $(ROOT_DIR)/src
TEX_DIR = $(SRC_DIR)/tex

DOC_TEX = $(foreach NUM, $(DOCS), $(TEX_DIR)/$(NUM)/$(NUM).tex)
DOC_PDF = $(foreach NUM, $(DOCS), $(BIN_DIR)/$(COURSE_NAME_FULL)-$(NUM)s.pdf)

.PHONY: all dirs docs bind tidy clean

all: dirs docs bind tidy

dirs:
	@mkdir -p $(BIN_DIR)

docs: $(DOC_PDF)

$(DOC_PDF): $(DOC_TEX)
	@printf "  $(@F)... "
	@pdflatex -halt-on-error -output-directory $(BIN_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%.tex) > /dev/null
	@pdflatex -halt-on-error -output-directory $(BIN_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%.tex) > /dev/null
	@echo "Done."

bind:
	@printf "  Binding documents... "
	@gs -dBATCH -dNOPAUSE -q -sDEVICE=pdfwrite -sOutputFile=$(BIN_DIR)/$(COURSE_NAME_FULL).pdf $(DOC_PDF) > /dev/null
	@echo "Done."

tidy:
	@printf "  Removing unneeded files... "
	@find $(BIN_DIR) -name '*.log' -delete
	@find $(BIN_DIR) -name '*.aux' -delete
	@find $(BIN_DIR) -name '*.out' -delete
	@echo "Done."

clean:
	@printf "  Removing $(BIN_DIR)... "
	@rm -rf $(BIN_DIR)
	@echo "Done."
