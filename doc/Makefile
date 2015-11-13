TOP_DIR = ..
DOC_DIR = $(TOP_DIR)/doc
SRC_DIR = $(DOC_DIR)/tex
BIN_DIR = $(TOP_DIR)/bin
OUT_DIR = $(BIN_DIR)/doc
DOCS = main

PATH_TEX = $(foreach FILE, $(DOCS), $(SRC_DIR)/$(FILE))
TEX   = $(addsuffix .tex, $(PATH_TEX))
PATH_PDF = $(foreach FILE, $(DOCS), $(OUT_DIR)/$(FILE))
PDF   = $(addsuffix .pdf, $(PATH_PDF))

.PHONY: clean docs tidy all

all: docs tidy

docs: $(OUT_DIR) $(PDF)

$(PDF): $(OUT_DIR)/%.pdf : $(SRC_DIR)/%.tex
	latexmk -pdf -pdflatex="pdflatex -interaction=nonstopmode" -output-directory=$(@D) -use-make $<

$(OUT_DIR):
	mkdir -p $(BIN_DIR)
	mkdir -p $(OUT_DIR)

clean: tidy
	rm -rf $(OUT_DIR)

tidy: $(OUT_DIR)
	find $(OUT_DIR) -name '*.log' -delete
	find $(OUT_DIR) -name '*.lof' -delete
	find $(OUT_DIR) -name '*.lot' -delete
	find $(OUT_DIR) -name '*.aux' -delete
	find $(OUT_DIR) -name '*.out' -delete
	find $(OUT_DIR) -name '*.toc' -delete
	find $(OUT_DIR) -name '*.fls' -delete
	find $(OUT_DIR) -name '*.fdb_latexmk' -delete
