DOCS = hw01 hw02 hw03 hw04 hw05 hw06 m01 f01
COURSE_NAME_FULL = umb-cs630-2014f

# find the path to directory where this Makefile lives
ROOT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
BUILD_DIR =$(shell dirname $(ROOT_DIR))/build/$(COURSE_NAME_FULL)
DOC_DIR = $(BUILD_DIR)/doc
LOG_DIR = $(BUILD_DIR)/log
TEX_DIR = $(ROOT_DIR)/src/tex

DOC_TEX = $(foreach NUM, $(DOCS), $(TEX_DIR)/$(NUM)/$(NUM).tex)
DOC_PDF = $(foreach NUM, $(DOCS), $(DOC_DIR)/$(COURSE_NAME_FULL)-$(NUM)s.pdf)

.PHONY: all dirs docs bind tidy clean

all: dirs docs bind tidy

dirs:
	@mkdir -p $(DOC_DIR)
	@mkdir -p $(LOG_DIR)

docs: $(DOC_PDF)

$(DOC_PDF): $(DOC_TEX)
	@printf "  $(@F)... "
	@pdflatex -halt-on-error -output-directory $(DOC_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%.tex) &> $(LOG_DIR)/$(@F:.pdf=.tex.log)
	@pdflatex -halt-on-error -output-directory $(DOC_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%.tex) > /dev/null
	@echo "Done."

bind:
	@printf "  Binding documents... "
	@gs -dBATCH -dNOPAUSE -q -sDEVICE=pdfwrite -sOutputFile=$(DOC_DIR)/$(COURSE_NAME_FULL).pdf $(DOC_PDF) > /dev/null
	@echo "Done."

tidy:
	@printf "  Removing unneeded files... "
	@find $(DOC_DIR) -name '*.log' -delete
	@find $(DOC_DIR) -name '*.aux' -delete
	@find $(DOC_DIR) -name '*.out' -delete
	@echo "Done."

clean:
	@printf "  Removing $(BUILD_DIR)... "
	@rm -rf $(BUILD_DIR)
	@echo "Done."
