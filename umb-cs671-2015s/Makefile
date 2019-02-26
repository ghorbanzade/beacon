DOCS = hw01 hw02 hw03 f01
CODS = f01
COURSE_NAME_FULL = umb-cs671-2015s

# find the path to directory where this Makefile lives
ROOT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
BUILD_DIR =$(shell dirname $(ROOT_DIR))/build/$(COURSE_NAME_FULL)
IMG_DIR = $(BUILD_DIR)/img
DOC_DIR = $(BUILD_DIR)/doc
LOG_DIR = $(BUILD_DIR)/log
TEX_DIR = $(ROOT_DIR)/src/tex
COD_DIR = $(ROOT_DIR)/src/r

DOC_TEX = $(foreach NUM, $(DOCS), $(TEX_DIR)/$(NUM)/$(NUM).tex)
DOC_PDF = $(foreach NUM, $(DOCS), $(DOC_DIR)/$(COURSE_NAME_FULL)-$(NUM)s.pdf)
COD_SRC = $(foreach NUM, $(CODS), $(COD_DIR)/$(NUM).r)
COD_PNG = $(foreach NUM, $(CODS), $(DOC_DIR)/$(COURSE_NAME_FULL)-$(NUM)-01.png)

.PHONY: all dirs code docs bind tidy clean

all: dirs code docs bind tidy

dirs:
	@mkdir -p $(DOC_DIR)
	@mkdir -p $(IMG_DIR)
	@mkdir -p $(LOG_DIR)

code: $(COD_PNG)

$(COD_PNG): $(COD_SRC)
	@printf "  Running $(@F:$(COURSE_NAME_FULL)-%-01.png=%.r)... "
	@Rscript --vanilla $(COD_DIR)/$(@F:$(COURSE_NAME_FULL)-%-01.png=%.r) $(IMG_DIR) > $(LOG_DIR)/$(@F:%-01.png=%.r.log) 2>&1
	@echo "Done."

docs: $(DOC_PDF)

$(DOC_PDF): $(DOC_TEX)
	@printf "  $(@F)... "
	@pdflatex -halt-on-error -output-directory $(DOC_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%.tex) > $(LOG_DIR)/$(@F:.pdf=.tex.log) 2>&1
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
