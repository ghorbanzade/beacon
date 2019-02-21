DOCS = hw02-slide hw02-report hw04-slide hw04-report
CODS = hw02 hw04
COURSE_NAME_FULL = umb-cs638-2016s

# find the path to directory where this Makefile lives
ROOT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
BIN_DIR = $(ROOT_DIR)/../bin/${COURSE_NAME_FULL}
SRC_DIR = $(ROOT_DIR)/src
COD_DIR = $(SRC_DIR)/r
TEX_DIR = $(SRC_DIR)/tex

DOC_TEX = $(foreach NUM, $(DOCS), $(TEX_DIR)/$(NUM).tex)
DOC_PDF = $(foreach NUM, $(DOCS), $(BIN_DIR)/$(COURSE_NAME_FULL)-$(NUM)s.pdf)
COD_SRC = $(foreach NUM, $(CODS), $(COD_DIR)/$(NUM).r)
COD_PNG = $(foreach NUM, $(CODS), $(BIN_DIR)/$(COURSE_NAME_FULL)-$(NUM)-01.png)

.PHONY: all dirs code docs bind tidy clean

all: dirs code docs bind tidy

dirs:
	@mkdir -p $(BIN_DIR)

code: $(COD_PNG)

$(COD_PNG): $(COD_SRC)
	@printf "  Running $(@F:$(COURSE_NAME_FULL)-%-01.png=%.r)... "
	@Rscript --vanilla $(COD_DIR)/$(@F:$(COURSE_NAME_FULL)-%-01.png=%.r) $(BIN_DIR) > /dev/null
	@echo "Done."

docs: $(DOC_PDF)

$(DOC_PDF): $(DOC_TEX)
	@printf "  $(@F)... "
	@pdflatex -halt-on-error -output-directory $(BIN_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%.tex) > /dev/null
	@pdflatex -halt-on-error -output-directory $(BIN_DIR) --jobname=$(@F:.pdf=) $(TEX_DIR)/$(@F:$(COURSE_NAME_FULL)-%s.pdf=%.tex) > /dev/null
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
	@find $(BIN_DIR) -name '*.vrb' -delete
	@find $(BIN_DIR) -name '*.snm' -delete
	@find $(BIN_DIR) -name '*.toc' -delete
	@find $(BIN_DIR) -name '*.nav' -delete
	@find $(BIN_DIR) -name '*.pyg' -delete
	@echo "Done."

clean:
	@printf "  Removing $(BIN_DIR)... "
	@rm -rf $(BIN_DIR)
	@echo "Done."
