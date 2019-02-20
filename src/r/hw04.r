#
# CS638: Applied Machine Learning
# Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
# Creative Commons Attribution-ShareAlike 4.0 International License
# More info: https://bitbucket.org/ghorbanzade/umb-cs638-2016s
#

# clear workspace
rm(list = ls())

# enable traceback on error messages
options(error=traceback)

# remove all plots if there are any
if (!is.null(dev.list())) trash <- dev.off()

# ---------------------------------------------------------
# function declarations
# ---------------------------------------------------------

# function to compute sigmoid for logistic regression
sigmoid <- function(z) {
	sigmoid <- 1 / (1 + exp(-z))
	return(sigmoid)
}

# function to compute cost for logistic regression
cost <- function(x, y, theta, lambda=0) {
	m <- nrow(x)
	hx <- sigmoid(x %*% theta)
	r <- (lambda / (2 * m)) * t(theta) %*% theta
	cost <- (1 / m) * sum(-y * log(hx) - (1 - y) * log(1 - hx)) + r
	return(cost)
}

# function to implement gradient descent algorithm
gradient_descent <- function(x, y, theta, alpha, iters, lambda=0) {
	m <- dim(x)[1]
	costs <- rep(NA, iters)
	for(i in 1:iters) {
		hx <- sigmoid(x %*% theta)
		theta[1,1] <- theta[1,1] - alpha * ((1 / m) * t(t(hx - y) %*% x[, 1]))
		theta[-1,1] <- theta[-1,1] - alpha * ((1 / m) * t(t(hx - y) %*% x[, -1]) + (lambda / m) * theta[-1, 1])
		costs[i] <- cost(x, y, theta, lambda=lambda)
	}
	out <- list(costs=costs, params=theta)
	return(out)
}

# function to map features into polynomial terms up to the nth power
build_features <- function(f1, f2, degree) {
	out <- matrix(rep(1, length(f1)))
	for (i in 1:degree) {
		for (j in 0:i) {
			out <- cbind(out, f1^(i-j) * f2^j)
		}
	}
	return(out)
}

# ---------------------------------------------------------
# loading datasets
# ---------------------------------------------------------

# load dataset 1
data1 = read.table("dat/hw04/data1.txt", sep=",")
colnames(data1) <- c('exam1', 'exam2', 'status')
data11 <- data1[data1[, "status"] == 1, , drop=FALSE]
data12 <- data1[data1[, "status"] == 0, , drop=FALSE]

# load dataset 2
data2 = read.table("dat/hw04/data2.txt", sep=",")
colnames(data2) <- c('test1', 'test2', 'status')
data21 <- data2[data2[, "status"] == 1, , drop=FALSE]
data22 <- data2[data2[, "status"] == 0, , drop=FALSE]

# ---------------------------------------------------------
# visualize dataset 1
# ---------------------------------------------------------

png(filename="bin/png/hw04-11.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(floor(min(data1$exam1)/5)*5,
	ceiling(max(data1$exam1)/5)*5,
	length.out=5
	)
yrange <- seq(floor(min(data1$exam2)/5)*5,
	ceiling(max(data1$exam2)/5)*5,
	length.out=5
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(data11$exam1, data11$exam2, type="p", col="blue", pch=3)
points(data12$exam1, data12$exam2, type="p", col="red", pch=1)
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

legend("topright",
	inset=0.01,
	legend = c(expression("admitted"), expression("rejected")),
	col = c("blue", "red"),
	pch = c(3, 1),
	cex = 0.8
	);
grid(10, 10)
box()
title(main="Student Admission Results Based on Exam Scores", font.main=1)
title(xlab="Score in Exam 1", col.lab='black')
title(ylab="Score in Exam 2", col.lab='black')
trash <- dev.off()

# ---------------------------------------------------------
# minimize cost function using gradient descent w/o scaling
# ---------------------------------------------------------

iters <- 1e6
alpha <- 1e-3
x <- cbind(1, data1$exam1, data1$exam2)
y <- data1$status
theta <- matrix(0, nrow=ncol(x), ncol=1)
fit <- gradient_descent(x, y, theta, alpha, iters)

# report final theta coefficients
paste("final theta coefficients:")
fit$params

# plot cost function based on number of iterations of gradient descent
# without feature scaling
png(filename="bin/png/hw04-12.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(0, iters, length.out=6)
yrange <- seq(0, 1, length.out=5)
plot(0, 0, type="n", axes=F, ann=F, xlim=range(xrange), ylim=range(yrange))
lines(fit$costs, col="blue", lty=1)
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

legend("topright", inset=0.01,
	legend = as.expression(bquote(alpha == .(alpha))),
	col = c("blue"),
	lty = c(1),
	cex = 0.8
	);
grid(10, 10)
box()
title(main=paste("Minimizing Cost Function using Gradient Descent Algorithm\n",
	"Without Feature Scaling"), font.main=1)
title(xlab="Number of Iterations", col.lab='black')
title(ylab="Cost Function", col.lab='black')
trash <- dev.off()

# ---------------------------------------------------------
# plot decision boundary
# ---------------------------------------------------------

theta <- fit$params
slope <- - theta[2] / theta[3]
intercept <- - theta[1] / theta[3]

png(filename="bin/png/hw04-13.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(floor(min(data1$exam1)/5)*5,
	ceiling(max(data1$exam1)/5)*5,
	length.out=5
	)
yrange <- seq(floor(min(data1$exam2)/5)*5,
	ceiling(max(data1$exam2)/5)*5,
	length.out=5
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(data11$exam1, data11$exam2, type="p", col="blue", pch=3)
points(data12$exam1, data12$exam2, type="p", col="red", pch=1)
abline(a=intercept, b=slope, col="green")
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

legend("topright",
	inset=0.01,
	legend = c(
		expression("admitted"),
		expression("rejected"),
		expression("decision boundary")
		),
	col = c("blue", "red", "green"),
	pch = c(3, 1, NA),
	lty = c(NA, NA, 1),
	cex = 0.8
	);
grid(10, 10)
box()
title(main=paste("Learned Decision Boundary for Student Admissions\n",
	"Based on Exam Scores"
	), font.main=1)
title(xlab="Score in Exam 1", col.lab='black')
title(ylab="Score in Exam 2", col.lab='black')
trash <- dev.off()

# ---------------------------------------------------------
# predict result based on learned decision boundary
# ---------------------------------------------------------

# What is the admission probability of a student with score
# 100 on exam1 and score 50 on exam2

student <- setNames(c(100, 50), c('exam1', 'exam2'))

theta <- fit$params
slope <- - theta[2] / theta[3]
intercept <- - theta[1] / theta[3]

probability <- sigmoid(c(1, student) %*% theta)
print(paste("Admission Chance: ", round(probability * 100, 2), "%"))

#
# plot student score relative to decision boundary
#
png(filename="bin/png/hw04-14.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(floor(min(data1$exam1)/5)*5,
	ceiling(max(data1$exam1)/5)*5,
	length.out=5
	)
yrange <- seq(floor(min(data1$exam2)/5)*5,
	ceiling(max(data1$exam2)/5)*5,
	length.out=5
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(data11$exam1, data11$exam2, type="p", col="blue", pch=3)
points(data12$exam1, data12$exam2, type="p", col="red", pch=1)
abline(a=intercept, b=slope, col="green")
points(student['exam1'], student['exam2'], type="p", col="violetred", pch=2)
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

legend("topright",
	inset=0.01,
	legend = c(
		expression("admitted"),
		expression("rejected"),
		expression("student score"),
		expression("decision boundary")
		),
	col = c("blue", "red", "violetred", "green"),
	pch = c(3, 1, 2, NA),
	lty = c(NA, NA, NA, 1),
	cex = 0.8
	);
grid(10, 10)
box()
title(main=paste("Decision Boundary for Student Admissions\n",
	"Based on Exam Scores"
	), font.main=1)
title(xlab="Score in Exam 1", col.lab='black')
title(ylab="Score in Exam 2", col.lab='black')
trash <- dev.off()

# ---------------------------------------------------------
# visualize dataset 2
# ---------------------------------------------------------

png(filename="bin/png/hw04-21.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(floor(min(data2$test1)*4)/4,
	ceiling(max(data2$test1)*4)/4,
	length.out=4
	)
yrange <- seq(floor(min(data2$test2)*4)/4,
	ceiling(max(data2$test2)*4)/4,
	length.out=4
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(data21$test1, data21$test2, type="p", col="blue", pch=3)
points(data22$test1, data22$test2, type="p", col="red", pch=1)
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

legend("topright",
	inset=0.01,
	legend = c(
		expression("passed"),
		expression("failed")
		),
	col = c("blue", "red"),
	pch = c(3, 1),
	cex = 0.8
	);
grid(10, 10)
box()
title(main="Acceptance Based on Test Results", font.main=1)
title(xlab="Test 1", col.lab='black')
title(ylab="Test 2", col.lab='black')
trash <- dev.off()

# ---------------------------------------------------------
# calculating costs and thetas for different lambda values
# ---------------------------------------------------------

features <- build_features(data2$test1, data2$test2, degree=7)
m <- dim(features)[1]
features <- cbind(rep(1, m), features)
features <- as.matrix(features)
n <- dim(features)[2]
y <- data2$status
theta <- matrix(0, ncol=1, nrow=n)

alpha <- 5e-1
iters <- 1e3
lambdas <- c(0, 1, 10, 100, 1000)
thetas <- matrix(0, ncol=length(lambdas), nrow=n)
costs <- matrix(0, ncol=length(lambdas), nrow=iters)

for (i in 1:length(lambdas)) {
	fit <- gradient_descent(features, y, theta, alpha, iters, lambdas[i])
	thetas[,i] <- fit$params
	costs[,i] <- fit$costs
}

# ---------------------------------------------------------
# plot cost function for different values of lambda
# ---------------------------------------------------------

# plot cost function based on number of iterations of gradient descent
# without feature scaling
png(filename="bin/png/hw04-22.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(0, iters, length.out=6)
yrange <- seq(0, 1, length.out=5)
plot(0, 0, type="n", axes=F, ann=F, xlim=range(xrange), ylim=range(yrange))
lines(costs[,1], col="blue", lty=1)
lines(costs[,2], col="red", lty=1)
lines(costs[,3], col="green", lty=1)
lines(costs[,4], col="blue", lty=2)
lines(costs[,5], col="red", lty=2)
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

legend("topright", inset=0.01,
	legend = c(
		as.expression(bquote(lambda == .(lambdas[1]))),
		as.expression(bquote(lambda == .(lambdas[2]))),
		as.expression(bquote(lambda == .(lambdas[3]))),
		as.expression(bquote(lambda == .(lambdas[4]))),
		as.expression(bquote(lambda == .(lambdas[5])))
		),
	col = c("blue", "red", "green", "blue", "red"),
	lty = c(1, 1, 1, 2, 2),
	cex = 0.8
	);
grid(10, 10)
box()
title(main=paste("Effect of lambda values on Cost Function Minimization"),
	font.main=1)
title(xlab="Number of Iterations", col.lab='black')
title(ylab="Cost Function", col.lab='black')
trash <- dev.off()

# ---------------------------------------------------------
# compute decision boundary for different lambda values
# ---------------------------------------------------------

mn1 = mean(data2$test1)
mn2 = mean(data2$test2)
r1 = max(data2$test1) - min(data2$test1)
r2 = max(data2$test2) - min(data2$test2)

num = 200;
u <- seq(mn1 - r1 / 2, mn1 + r1 / 2, len=num)
v <- seq(mn2 - r2 / 2, mn2 + r2 / 2, len=num)

z1 = matrix(0, length(u), length(v))
z2 = matrix(0, length(u), length(v))
z3 = matrix(0, length(u), length(v))
z4 = matrix(0, length(u), length(v))
for (i in 1:length(u)) {
	for (j in 1:length(v)) {
		z1[j, i] <-  cbind(1, build_features(u[i], v[j], 7)) %*% thetas[,1]
		z2[j, i] <-  cbind(1, build_features(u[i], v[j], 7)) %*% thetas[,2]
		z3[j, i] <-  cbind(1, build_features(u[i], v[j], 7)) %*% thetas[,3]
		z4[j, i] <-  cbind(1, build_features(u[i], v[j], 7)) %*% thetas[,4]
	}
}

# ---------------------------------------------------------
# plot decision boundaries for different lambda values
# ---------------------------------------------------------

png(filename="bin/png/hw04-23.png",
	height=768, width=1024, res=150, units="px", bg="white")

contour(u, v, z1, nlevels=0, col="green", lty=2)

points(data21$test1, data21$test2, type="p", col="blue", pch=3)
points(data22$test1, data22$test2, type="p", col="red", pch=1)

legend("topright",
	inset=0.01,
	legend = c(
		expression("passed"),
		expression("failed"),
		as.expression(bquote(lambda == .(lambdas[1])))
		),
	col = c("blue", "red", "green"),
	pch = c(3, 1, NA),
	lty = c(NA, NA, 2),
	cex = 0.8
	);
grid(10, 10)
box()
title(main="Decision Boundary of Acceptance Based on Test Results", font.main=1)
title(xlab="Test 1", col.lab='black')
title(ylab="Test 2", col.lab='black')
trash <- dev.off()

# plot all contours on the same figure for comparison
png(filename="bin/png/hw04-24.png",
	height=768, width=1024, res=150, units="px", bg="white")

contour(u, v, z1, nlevels=0, col="green", lty=2)
contour(u, v, z2, nlevels=0, col="orange", lty=2, add=TRUE)
contour(u, v, z3, nlevels=0, col="blue", lty=2, add=TRUE)
contour(u, v, z4, nlevels=0, col="red", lty=2, add=TRUE)

points(data21$test1, data21$test2, type="p", col="blue", pch=3)
points(data22$test1, data22$test2, type="p", col="red", pch=1)

legend("topright",
	inset=0.01,
	legend = c(
		expression("passed"),
		expression("failed"),
		as.expression(bquote(lambda == .(lambdas[1]))),
		as.expression(bquote(lambda == .(lambdas[2]))),
		as.expression(bquote(lambda == .(lambdas[3]))),
		as.expression(bquote(lambda == .(lambdas[4])))
		),
	col = c("blue", "red", "green", "orange", "blue", "red"),
	pch = c(3, 1, NA, NA, NA, NA),
	lty = c(NA, NA, 2, 2, 2, 2),
	cex = 0.8
	);
grid(10, 10)
box()
title(main="Decision Boundary for Acceptance Based on Test Results", font.main=1)
title(xlab="Test 1", col.lab='black')
title(ylab="Test 2", col.lab='black')
trash <- dev.off()
