#!/usr/bin/Rscript
library(ggplot2)

data = read.csv(commandArgs(TRUE)[1])

f1 <- function(x) 1
f2 <- function(x) x
f3 <- function(x) x^2
f4 <- function(x) log(x, 2)
f5 <- function(x) x * log(x, 2)

p <-
    ggplot(data,aes(size, average)) +
    ggtitle("Size vs. Time") +
    theme(plot.title = element_text(size=20, face="bold",
                                    margin=margin(10, 0, 10, 0), hjust=0.5)) +
    coord_cartesian(xlim=c(0, max(data$size)), ylim=c(0, max(data$average))) +
    geom_point(color="midnightblue") +
    geom_smooth(method = "auto") +

    stat_function(fun=f1, geom="line", aes(color="f1")) +
    stat_function(fun=f2, geom="line", aes(color="f2")) +
    stat_function(fun=f3, geom="line", aes(color="f3")) +
    stat_function(fun=f4, geom="line", aes(color="f4")) +
    stat_function(fun=f5, geom="line", aes(color="f5")) +

    scale_color_manual(name = "Efficiency",
                       values = c("black", "red", "green", "orange", "purple"),
                       labels = c("O(1)", "O(n)", expression(O(n^2)),
                                  "O(log(n))", "O(n log(n))"))

ggsave("plot.png")

