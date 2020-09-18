package project.spline
import java.util.Arrays
/**
*import project.spline.solveTridiagonal
*import project.spline.interpolate
*/


fun main(){
    solveTridiagonal(A, b)
}

/** Linear algebra */
operator fun Array<Double>.times(a: Array<Double>) = Array<Double>(this.size){i -> this[i] * a[i]}
operator fun Array<Double>.times(a: Double) = Array<Double>(this.size){i -> this[i] * a }
operator fun Double.times(a: Array<Double>) = Array<Double>(a.size){i -> a[i] * this }







val A = listOf<DoubleArray>( doubleArrayOf(1.0, 1.0), doubleArrayOf(2.0, 2.0, 2.0), doubleArrayOf(1.0, 1.0))



var a = doubleArrayOf(1.0 , 2.0, 3.0 )
val b = doubleArrayOf(4.0, 11.0, 8.0)
val d = 5.0
val c = a * d
val L = listOf(a, b,c)



val t = Array<Double>(100){i -> i.toDouble()/100}
val y = t.forEach{java.lang.Math.cos(it)}

fun solveTridiagonal(A : List<DoubleArray>,  b : DoubleArray ): DoubleArray {
    /*
    * Implements the Thomas algorithm for solving the tri-diagonal linear system.
    * https://en.wikipedia.org/wiki/Tridiagonal_matrix_algorithm.
    * Only stable in the diagonally dominant or positive definite case.
    *Ax = b, where A = diag(d_lower , d_diag, d_upper).
    *
    * Returns: x: Array<Double>
               solution
    */
    println(A)

    val d_lower = A[0]
    val d_diag = A[1]
    val d_upper = A[2]
    val n = d_diag.size
    val d_upper_temp = DoubleArray(n-1)
    val b_temp = DoubleArray(n)

    d_upper_temp[0] = d_upper[0]/d_diag[0]
    b_temp[0] = b[0]/d_diag[0]
    for (i in 1 .. n - 2) {
        d_upper_temp[i] = d_upper[i]/(d_diag[i]-d_lower[i]*d_upper_temp[i-1])
        b_temp[i] = (b[i] - b_temp[i-1] * d_lower[i] )/(d_diag[i] - d_lower[i] * d_upper_temp[i-1])
        println(i.toString(i))}
    b[n-1] = (b[n-1] - b_temp[n-2] * d_lower[n-2])/(d_diag[n-1] - d_lower[n-1] * d_upper_temp[n-2])
    val x = DoubleArray(n)
    x[n-1] = b_temp[n-1]
    for (i in n-2 downTo 0) {
        x[i] = (b_temp[i] - d_upper[i] * x[i+1])/d_diag[i]
    }
    return x
}

