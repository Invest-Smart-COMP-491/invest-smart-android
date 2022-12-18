package com.comp491.investsmart.ui.assetdetail
/*
val priceData = parseData(csvContent)
val current = priceData.filter {
    it.city == "Chicago" && it.year == 2017
}

append {
    div {
        val vc = newVizContainer().apply {
            size = Size(width, height)
        }
        vc.chart(current) {

            val dayDimension = discrete({ stock.day })
            val priceDimension = quantitative({ stock.price })

            bar(dayDimension, priceDimension)
        }
    }
}

 */

import io.data2viz.charts.chart.chart
import io.data2viz.charts.chart.mark.plot
import io.data2viz.charts.chart.quantitative
import io.data2viz.charts.viz.newVizContainer
import io.data2viz.geom.Size
import kotlinx.html.div
import kotlinx.html.dom.append
import org.w3c.dom.Node

private val width = 300.0
private val height = 300.0

private val values = listOf(1.0, 2.0, 3.0, 4.0, 5.0)

fun Node.myFirstChart() {
    append {
        div {
            newVizContainer().apply {
                size = Size(width, height)
                chart(values) {
                    val values = quantitative({ domain })
                    plot(values, values)
                }
            }
        }
    }
}