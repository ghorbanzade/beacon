#
# CS671: Machine Learning
# Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
# Creative Commons Attribution-ShareAlike 4.0 International License
# More info: https://bitbucket.org/ghorbanzade/umb-cs671-2015f
#

# load dataset
letters <- read.csv("dat/letter.csv", header=TRUE, sep=",")

# show summary of loaded dataset on console
# str(letters)
# alternatively, to view the dataset in RStudio
# View(letters)

# R packages specialized in SVM are Kernlab, svmlight, libsvm, e1071
# We begin with kernlab
# Make sure kernlab package is installed
# list(sort(.packages(all.available = TRUE)))
# pkg <- select.list(sort(.packages(all.available = TRUE)))
# if(nchar(pkg)) library(pkg, character.only=TRUE)
library(kernlab)

# Select first 16000 samples as training dataset
letters_train <- letters[1:16000, ]

# Select the remaining 4000 samples as test dataset
letters_test <- letters[16001:20000, ]

# Build a classifer using a linear kernel function
letter_classifier <- ksvm(lettr~., data=letters_train, kernel="vanilladot")

# show classifier properties
# letter_classifier

# apply classifier on the test dataset
letter_prediction <- predict(letter_classifier, letters_test)

# show header
head(letter_prediction)

# show confusion matrix
table(letter_prediction, letters_test$lettr)

# show number of correct and incorrect recognitions
agreement <- letter_prediction == letters_test$lettr
table(agreement)
