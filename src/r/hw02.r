#
# CS638: Applied Machine Learning
# Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
# Creative Commons Attribution-ShareAlike 4.0 International License
# More info: https://bitbucket.org/ghorbanzade/umb-cs638-2016s
#

# clear workspace
rm(list = ls())

# remove all plots if there are any
if (!is.null(dev.list())) trash <- dev.off()

# loading database
data = read.table("dat/hw02/data.txt", sep=",")
colnames(data) <- c('population', 'profit')

##
# visualize dataset
##
png(filename="bin/png/hw02-01.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(floor(min(data$population)/5)*5,
	ceiling(max(data$population)/5)*5,
	length.out=5
	)
yrange <- seq(floor(min(data$profit)/5)*5,
	ceiling(max(data$profit)/5)*5,
	length.out=7
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(data$population, data$profit, type="p", col="blue")
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

grid(10, 10)
box()
title(main="Profit of Coffee Shop Based on City's Population", font.main=1)
title(xlab="Population in 10,000", col.lab='black')
title(ylab="Profit in $10,000", col.lab='black')
trash <- dev.off()

##
# Check feature correlation
##
with(data, cor.test(data$population, data$profit, alternative="greater", conf.level=.8))

##
# data variables in pairs to check for correlation easily
##
png(filename="bin/png/hw02-02.png",
	height=768, width=1024, res=150, units="px", bg="white")
pairs(data)
trash <- dev.off()

##
# fit linear model and print summary regression
##
lm.out <- lm(data$profit ~ data$population)
summary(lm.out)

##
# Plot regression line on the dataset
##
png(filename="bin/png/hw02-03.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(floor(min(data$population)/5)*5,
	ceiling(max(data$population)/5)*5,
	length.out=5
	)
yrange <- seq(floor(min(data$profit)/5)*5,
	ceiling(max(data$profit)/5)*5,
	length.out=7
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(data$population, data$profit, type="p", col="blue")
abline(lm.out, col="red")
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

grid(10, 10)
box()
title(main="Profit of Coffee Shop Based on City's Population", font.main=1)
title(xlab="Population in 10,000", col.lab='black')
title(ylab="Profit in $10,000", col.lab='black')
trash <- dev.off()

##
# plot linear regression model summary
##
png(filename="bin/png/hw02-04.png",
    height=768, width=1024, res=150, units="px", bg="white")
par(mfrow=c(2,2))
plot(lm.out)
trash <- dev.off()

##
# compute value of the cost function for a given theta
##
lm.out <- lm(data$profit ~ data$population)
theta <- coef(lm.out)
x <- cbind(1,data$population)
y <- data$profit
m <- nrow(data)
cost <- sum(((x%*%theta)- y)^2)/(2*m)

##
# Function to find theta values obtained via gradient descent method
# for a given learning rate
##
gradient_descent <- function(x, y, alpha) {
	x <- cbind(1, x)
	m <- nrow(x)
	iterations <- 500
	theta <- c(0, 0)
	for (i in 1:iterations)
	{
		theta[1] <- theta[1] - alpha * (1/m) * sum(((x%*%theta)- y))
		theta[2] <- theta[2] - alpha * (1/m) * sum(((x%*%theta)- y)*x[,2])
	}
	return(theta)
}

##
# Find an report regression coefficient
##
theta <- gradient_descent(data$population, data$profit, 0.02)
theta <- gradient_descent(data$population, data$profit, 0.005)

##
# Compute theta values for different learning rates
##
realtheta <- coef(lm(data$profit ~ data$population))
min <- 0.001
max <- 0.024
num <- 20
alpha <- seq(min, max, length.out=num)
theta <- mat.or.vec(num, 2)
nrmtheta <- mat.or.vec(num, 2)
for (i in 1:length(alpha)) {
	theta[i,] <- gradient_descent(data$population, data$profit, alpha[i])
	nrmtheta[i,] <- (realtheta - theta[i,]) / realtheta * 100
}

##
# Plot theta_0 and theta_1 for different learning rates
##
png(filename="bin/png/hw02-05.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(min(alpha),
	max(alpha),
	length.out=5
	)
yrange <- seq(floor(min(theta, realtheta)),
	ceiling(max(theta, realtheta)),
	length.out=7
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
lines(alpha, theta[,1], type="o", pch=21, col="blue")
lines(alpha, theta[,2], type="o", pch=22, col="red")
abline(h=realtheta[1], col="blue", lty=3)
abline(h=realtheta[2], col="red", lty=3)
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))
legend(0.015, 0.5,
	cex=1,
	c(expression(paste(theta[0], " gradient descent")),
		expression(paste(theta[1], " gradient descent")),
		expression(paste(theta[0], " regression line")),
		expression(paste(theta[1], " regression line"))
	),
	col=c("blue","red","blue","red"),
	pch=c(21, 22, 21, 22),
	lty=c(1, 2, 3, 3));

grid(10, 10)
box()
title(main="Impact of different learning rate on hypothesis function coefficients", font.main=1)
title(xlab=expression(paste("Learning Rate (", alpha, ")")), col.lab='black')
title(ylab=expression(paste("Hypothesis Function Coefficients (", theta, ")")), col.lab='black')
trash <- dev.off()

##
# Plot normalized gradient descent error for different learning rates
##
png(filename="bin/png/hw02-06.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(min(alpha), max(alpha), length.out=5)
yrange <- seq(0, 100, length.out=6)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
lines(alpha, nrmtheta[,1], type="o", col="blue")
lines(alpha, nrmtheta[,2], type="o", pch=22, lty=2, col="red")
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=paste(round(yrange, 3), "%", sep=""))
legend("topright", inset=0.05,
	legend = c(expression(theta[0]), expression(theta[1])),
	col=c("blue","red"),
	pch=c(21, 22),
	lty=c(1, 2));

grid(10, 10)
box()
title(main="Impact of different learning rate on values of\n hypothesis function coefficients after 500 iterations", font.main=1)
title(xlab=expression(paste("Learning Rate (", alpha, ")")), col.lab='black')
title(ylab=expression(paste("Normalized Error")), col.lab='black')
trash <- dev.off()

##
#
##
get_iterations <- function(x, y, alpha, realtheta) {
	x <- cbind(1, x)
	m <- nrow(x)
	theta <- c(0, 0)
	counter <- 0
	satisfy <- FALSE
	while (!all(abs(realtheta - theta)/realtheta*100 < c(1, 1))) {
		theta[1] <- theta[1] - alpha * (1/m) * sum(((x%*%theta)- y))
		theta[2] <- theta[2] - alpha * (1/m) * sum(((x%*%theta)- y)*x[,2])
		counter <- counter + 1
	}
	return(counter)
}

##
# compute required number of iterations to acheive less than 1 percent error
# for different learning rates
##
min <- 0.001
max <- 0.024
num <- 20
alpha <- seq(min, max, length.out=num)
iterations <- vector(mode="numeric", num)
for (i in 1:length(alpha)) {
	iterations[i] <- get_iterations(data$population, data$profit, alpha[i], realtheta)
}

##
# plot required number of iterations for different learning rates
##
png(filename="bin/png/hw02-07.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(min(alpha), max(alpha), length.out=5)
yrange <- seq(floor(min(iterations)/5000)*5000,
	ceiling(max(iterations)/5000)*5000,
	length.out=5
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
lines(alpha, iterations, type="o", col="blue")
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

grid(10, 10)
box()
title(main="Required number of iterations for different learning rates\n to achieve less than one percent error", font.main=1)
title(xlab=expression(paste("Learning Rate (", alpha, ")")), col.lab='black')
title(ylab="Required Number of Iterations", col.lab='black')
trash <- dev.off()

##
# predict profit of store, given population of the city
##
given_population <- 645966 * 1e-4
predicted_profit <- c(1, given_population) %*% realtheta
predicted_profit

new_row <- cbind(given_population, predicted_profit)
colnames(new_row) <- c('population', 'profit')
new_data <- rbind(data, new_row)

##
# visualize dataset and include the estimated profit for Boston
##
png(filename="bin/png/hw02-08.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(floor(min(new_data$population)/5)*5,
	ceiling(max(new_data$population)/5)*5,
	length.out=5
	)
yrange <- seq(floor(min(new_data$profit)/5)*5,
	ceiling(max(new_data$profit)/5)*5,
	length.out=7
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(data$population, data$profit, type="p", col="blue")
abline(lm(data$profit ~ data$population), col="red")
points(new_row[1], new_row[2], type="p", col="green")
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

grid(10, 10)
box()
title(main="Profit of Coffee Shop Based on City's Population", font.main=1)
title(xlab="Population in 10,000", col.lab='black')
title(ylab="Profit in $10,000", col.lab='black')
trash <- dev.off()
