DOCS = hw02-report hw02-slide
CODS = hw02

TOP_DIR = .
BIN_DIR = $(TOP_DIR)/bin
SRC_DIR = $(TOP_DIR)/src
COD_DIR = $(SRC_DIR)/r
TEX_DIR = $(SRC_DIR)/tex
DOC_DIR = $(BIN_DIR)/doc
PNG_DIR = $(BIN_DIR)/png

DOC_TEX = $(foreach NUM, $(DOCS), $(TEX_DIR)/$(NUM).tex)
DOC_PDF = $(foreach NUM, $(DOCS), $(DOC_DIR)/$(NUM).pdf)
COD_SRC = $(foreach NUM, $(CODS), $(COD_DIR)/$(NUM).r)
COD_PNG = $(foreach NUM, $(CODS), $(PNG_DIR)/$(NUM)-01.png)

.PHONY: all dirs code docs bind tidy clean

all: dirs code docs bind tidy

dirs:
	@mkdir -p $(BIN_DIR)
	@mkdir -p $(DOC_DIR)
	@mkdir -p $(PNG_DIR)

code: $(COD_PNG)

$(COD_PNG): $(COD_SRC)
	@echo -n "  Running $(?F)... "
	@Rscript $? > /dev/null
	@echo "Done."

docs: $(DOC_PDF)

$(DOC_PDF): $(DOC_TEX)
	@echo -n "  $(@F)... "
	@cd $(DOC_DIR) && \
	pdflatex -halt-on-error -shell-escape ../../$(TEX_DIR)/$(@F:.pdf=.tex) > /dev/null && \
	pdflatex -halt-on-error -shell-escape ../../$(TEX_DIR)/$(@F:.pdf=.tex) > /dev/null
	@echo "Done."

bind:
	@echo -n "  Binding documents... "
	@gs -dBATCH -dNOPAUSE -q -sDEVICE=pdfwrite -sOutputFile=$(BIN_DIR)/cs638.pdf $(DOC_PDF)
	@echo "Done."

tidy:
	@echo -n "  Removing unneeded files... "
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
	@echo -n "  Removing $(BIN_DIR)... "
	@rm -rf $(BIN_DIR)
	@echo "Done."
