SRC_DIR = src
OUT_DIR = bin
DOCS = hw01 hw02 hw03

PATH_TEX = $(foreach FILE, $(DOCS), $(SRC_DIR)/$(FILE)/$(FILE))
TEX   = $(addsuffix .tex, $(PATH_TEX))
PATH_PDF = $(foreach FILE, $(DOCS), $(OUT_DIR)/$(FILE)/$(FILE))
PDF   = $(addsuffix .pdf, $(PATH_PDF))

.PHONY: clean docs tidy all

all: $(OUT_DIR) docs tidy

docs: $(PDF)
	gs -dBATCH -dNOPAUSE -q -sDEVICE=pdfwrite -sOutputFile=documents.pdf $(PDF)

$(PDF): $(OUT_DIR)/%.pdf : $(SRC_DIR)/%.tex
	mkdir -p $(@D)
	latexmk -pdf -pdflatex="pdflatex -interaction=nonstopmode" -output-directory=$(@D) -use-make $<

$(OUT_DIR):
	mkdir -p $(OUT_DIR)

clean: tidy
	find . -name '*.pdf' -delete
	rm -r $(OUT_DIR)

tidy:
	find . -name '*.log' -delete
	find . -name '*.aux' -delete
	find . -name '*.out' -delete
	find . -name '*.fls' -delete
	find . -name '*.fdb_latexmk' -delete
