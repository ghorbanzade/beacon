#####################################################################
# CS671: Machine Learning
# Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
# More info: https://bitbucket.org/ghorbanzade/beacon
#####################################################################

# Load dataset
letters <- read.csv("dat/iris.csv", header=TRUE, sep=",")

# show summary of loaded dataset on console
str(letters)

# alternatively, to view the dataset in RStudio
# View(letters)

# We first need to randomize the dataset
# To do it, we first generate 150 numbers in the interval [0,1]
rand <- runif(150)

# Then we replace them with their order,
# as if we generated 150 random integer numbers from 1 to 150
rand <- order(rand)

# Then we shuffle the dataset, using the random array
iris_rand <- iris[rand, ]

# R packages specialized in SVM are Kernlab, svmlight, libsvm, e1071
# We use kernlab
# Make sure kernlab package is installed
# pkg <- select.list(sort(.packages(all.available = TRUE)), graphics=TRUE)
# if(nchar(pkg)) library(pkg, character.only=TRUE)
# library("kernlab")

# Select first 120 samples as training dataset
iris_train <- iris_rand[1:120, ]

# Select the remaining 30 samples as test dataset
iris_test <- iris_rand[121:150, ]

# Build a classifer using a linear kernel function
iris_classifier <- ksvm(Species~., data=iris_train, kernel="vanilladot")

# show classifier properties
iris_classifier

# apply classifier on the test dataset
iris_prediction <- predict(iris_classifier, iris_test)

# show header
head(iris_prediction)

# show confusion matrix
table(iris_prediction, iris_test$Species)

# show number of correct and incorrect recognitions
agreement <- iris_prediction == iris_test$Species
table(agreement)
