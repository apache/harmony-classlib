/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package java.lang;


/**
 * Class math provides various floating point support routines and some standard
 * constants.
 */
public final class Math {
	
	private static final int FLOAT_EXPONENT_BIAS = 127;

    private static final int FLOAT_EXPONENT_MASK = 0x7F800000;

    private static final int DOUBLE_NON_MANTISSA_BITS = 12;

    private static final int DOUBLE_MANTISSA_BITS = 52;

    private static final int FLOAT_NON_MANTISSA_BITS = 9;

    private static final int FLOAT_MANTISSA_BITS = 23;

    private static final int DOUBLE_EXPONENT_BIAS = 1023;

    private static final long DOUBLE_EXPONENT_MASK = 0x7ff0000000000000L;

    private static final int FLOAT_MANTISSA_MASK = 0x007fffff;

    private static final int FLOAT_SIGN_MASK = 0x80000000;

    private static final long DOUBLE_MANTISSA_MASK = 0x000fffffffffffffL;

    private static final long DOUBLE_SIGN_MASK = 0x8000000000000000L;
    
	/**
	 * Standard math constants.
	 */
	public static final double E = 2.718281828459045;

	public static final double PI = 3.141592653589793;

	private static java.util.Random random;

	/**
	 * Prevents this class from being instantiated.
	 */
	private Math() {
	}

	/**
	 * Answers the absolute value of the argument.
	 * 
	 * @param d
	 *            the value to be converted
	 * @return the argument if it is positive, otherwise the negation of the
	 *         argument.
	 */
	public static double abs(double d) {
		long bits = Double.doubleToLongBits(d);
		bits &= 0x7fffffffffffffffL;
		return Double.longBitsToDouble(bits);
	}

	/**
	 * Answers the absolute value of the argument.
	 * 
	 * @param f
	 *            the value to be converted
	 * @return the argument if it is positive, otherwise the negation of the
	 *         argument.
	 */
	public static float abs(float f) {
		int bits = Float.floatToIntBits(f);
		bits &= 0x7fffffff;
		return Float.intBitsToFloat(bits);
	}

	/**
	 * Answers the absolute value of the argument.
	 * 
	 * @param i
	 *            the value to be converted
	 * @return the argument if it is positive, otherwise the negation of the
	 *         argument.
	 */
	public static int abs(int i) {
		return i >= 0 ? i : -i;
	}

	/**
	 * Answers the absolute value of the argument.
	 * 
	 * @param l
	 *            the value to be converted
	 * @return the argument if it is positive, otherwise the negation of the
	 *         argument.
	 */
	public static long abs(long l) {
		return l >= 0 ? l : -l;
	}

	/**
	 * Answers the closest double approximation of the arc cosine of the
	 * argument
	 * 
	 * @param d
	 *            the value to compute acos of
	 * @return the arc cosine of the argument.
	 */
	public static native double acos(double d);

	/**
	 * Answers the closest double approximation of the arc sine of the argument
	 * 
	 * @param d
	 *            the value to compute asin of
	 * @return the arc sine of the argument.
	 */
	public static native double asin(double d);

	/**
	 * Answers the closest double approximation of the arc tangent of the
	 * argument
	 * 
	 * @param d
	 *            the value to compute atan of
	 * @return the arc tangent of the argument.
	 */
	public static native double atan(double d);

	/**
	 * Answers the closest double approximation of the arc tangent of the result
	 * of dividing the first argument by the second argument.
	 * 
	 * @param d1
	 *            the numerator of the value to compute atan of
	 * @param d2
	 *            the denominator of the value to compute atan of
	 * @return the arc tangent of d1/d2.
	 */
	public static native double atan2(double d1, double d2);
    
    /**
     * Answers the closest double approximation of the cube root of the
     * argument. The final result should be within 1ulp of the real result.
     * 
     * @param d
     *            the value to compute cube root of
     * @return the cube root of the argument.
     */
    public static native double cbrt(double d);

	/**
	 * Answers the double conversion of the most negative (i.e. closest to
	 * negative infinity) integer value which is greater than the argument.
	 * 
	 * @param d
	 *            the value to be converted
	 * @return the ceiling of the argument.
	 */
	public static native double ceil(double d);

	/**
	 * Answers the closest double approximation of the cosine of the argument
	 * 
	 * @param d
	 *            the value to compute cos of
	 * @return the cosine of the argument.
	 */
	public static native double cos(double d);
    
    /**
     * Answers the closest double approximation of the hyperbolic cosine of the
     * argument. The final result should be within 2.5ulps of the real result.
     * 
     * @param d
     *            the value to compute hyperbolic cosine of
     * @return the hyperbolic cosine of the argument.
     */
    public static native double cosh(double d);

	/**
	 * Answers the closest double approximation of the raising "e" to the power
	 * of the argument
	 * 
	 * @param d
	 *            the value to compute the exponential of
	 * @return the exponential of the argument.
	 */
	public static native double exp(double d);
    
    /**
     * Answers the closest double approximation of <i>e</i><sup>d</sup> - 1.
     * If the argument is very close to 0, it is much more accurate to use
     * expm1(d)+1 than exp(d).
     * 
     * The final result should be within 1 ulp of the real result. For any
     * finite input, the result should be no less than -1.0. If the real result
     * is within 0.5 ulp of -1, -1.0 should be answered.
     * 
     * @param d
     *            the value to compute the <i>e</i><sup>d</sup> - 1 of
     * @return the <i>e</i><sup>d</sup> - 1 value of the argument.
     */
    public static native double expm1(double d);

	/**
	 * Answers the double conversion of the most positive (i.e. closest to
	 * positive infinity) integer value which is less than the argument.
	 * 
	 * @param d
	 *            the value to be converted
	 * @return the ceiling of the argument.
	 */
	public static native double floor(double d);
    
    /**
     * Answers sqrt(<i>x</i><sup>2</sup>+<i>y</i><sup>2</sup>). The
     * final result is without medium underflow or overflow.
     * 
     * The final result should be within 1 ulp of the real result. If one
     * parameter remains constant, the result should be semi-monotonic.
     * 
     * @param x
     *            a double number
     * @param y
     *            a double number
     * @return the sqrt(<i>x</i><sup>2</sup>+<i>y</i><sup>2</sup>) value
     *         of the arguments.
     */
    public static native double hypot(double x, double y);

	/**
	 * Answers the remainder of dividing the first argument by the second using
	 * the IEEE 754 rules.
	 * 
	 * @param d1
	 *            the numerator of the operation
	 * @param d2
	 *            the denominator of the operation
	 * @return the result of d1/d2.
	 */
	public static native double IEEEremainder(double d1, double d2);

	/**
	 * Answers the closest double approximation of the natural logarithm of the
	 * argument
	 * 
	 * @param d
	 *            the value to compute the log of
	 * @return the natural logarithm of the argument.
	 */
	public static native double log(double d);

    /**
     * Answers the closest double approximation of the base 10 logarithm of the
     * argument
     * 
     * @param d
     *            the value to compute the log10 of
     * @return the natural logarithm of the argument.
     */
    public static native double log10(double d);
    
    /**
     * Answers the closest double approximation of the natural logarithm of the
     * sum of the argument and 1. If the argument is very close to 0, it is much
     * more accurate to use log1p(d) than log(1.0+d).
     * 
     * The final result should be within 1 ulp of the real result and be
     * semi-monotonic.
     * 
     * @param d
     *            the value to compute the ln(1+d) of
     * @return the natural logarithm of the sum of the argument and 1.
     */
    public static native double log1p(double d);

	/**
	 * Answers the most positive (i.e. closest to positive infinity) of the two
	 * arguments.
	 * 
	 * @param d1
	 *            the first argument to check
	 * @param d2
	 *            the second argument
	 * @return the larger of d1 and d2.
	 */
	public static double max(double d1, double d2) {
		if (d1 > d2)
			return d1;
		if (d1 < d2)
			return d2;
		/* if either arg is NaN, return NaN */
		if (d1 != d2)
			return Double.NaN;
		/* max( +0.0,-0.0) == +0.0 */
		if (d1 == 0.0
				&& ((Double.doubleToLongBits(d1) & Double.doubleToLongBits(d2)) & 0x8000000000000000L) == 0)
			return 0.0;
		return d1;
	}

	/**
	 * Answers the most positive (i.e. closest to positive infinity) of the two
	 * arguments.
	 * 
	 * @param f1
	 *            the first argument to check
	 * @param f2
	 *            the second argument
	 * @return the larger of f1 and f2.
	 */
	public static float max(float f1, float f2) {
		if (f1 > f2)
			return f1;
		if (f1 < f2)
			return f2;
		/* if either arg is NaN, return NaN */
		if (f1 != f2)
			return Float.NaN;
		/* max( +0.0,-0.0) == +0.0 */
		if (f1 == 0.0f
				&& ((Float.floatToIntBits(f1) & Float.floatToIntBits(f2)) & 0x80000000) == 0)
			return 0.0f;
		return f1;
	}

	/**
	 * Answers the most positive (i.e. closest to positive infinity) of the two
	 * arguments.
	 * 
	 * @param i1
	 *            the first argument to check
	 * @param i2
	 *            the second argument
	 * @return the larger of i1 and i2.
	 */
	public static int max(int i1, int i2) {
		return i1 > i2 ? i1 : i2;
	}

	/**
	 * Answers the most positive (i.e. closest to positive infinity) of the two
	 * arguments.
	 * 
	 * @param l1
	 *            the first argument to check
	 * @param l2
	 *            the second argument
	 * @return the larger of l1 and l2.
	 */
	public static long max(long l1, long l2) {
		return l1 > l2 ? l1 : l2;
	}

	/**
	 * Answers the most negative (i.e. closest to negative infinity) of the two
	 * arguments.
	 * 
	 * @param d1
	 *            the first argument to check
	 * @param d2
	 *            the second argument
	 * @return the smaller of d1 and d2.
	 */
	public static double min(double d1, double d2) {
		if (d1 > d2)
			return d2;
		if (d1 < d2)
			return d1;
		/* if either arg is NaN, return NaN */
		if (d1 != d2)
			return Double.NaN;
		/* min( +0.0,-0.0) == -0.0 */
		if (d1 == 0.0
				&& ((Double.doubleToLongBits(d1) | Double.doubleToLongBits(d2)) & 0x8000000000000000l) != 0)
			return 0.0 * (-1.0);
		return d1;
	}

	/**
	 * Answers the most negative (i.e. closest to negative infinity) of the two
	 * arguments.
	 * 
	 * @param f1
	 *            the first argument to check
	 * @param f2
	 *            the second argument
	 * @return the smaller of f1 and f2.
	 */
	public static float min(float f1, float f2) {
		if (f1 > f2)
			return f2;
		if (f1 < f2)
			return f1;
		/* if either arg is NaN, return NaN */
		if (f1 != f2)
			return Float.NaN;
		/* min( +0.0,-0.0) == -0.0 */
		if (f1 == 0.0f
				&& ((Float.floatToIntBits(f1) | Float.floatToIntBits(f2)) & 0x80000000) != 0)
			return 0.0f * (-1.0f);
		return f1;
	}

	/**
	 * Answers the most negative (i.e. closest to negative infinity) of the two
	 * arguments.
	 * 
	 * @param i1
	 *            the first argument to check
	 * @param i2
	 *            the second argument
	 * @return the smaller of i1 and i2.
	 */
	public static int min(int i1, int i2) {
		return i1 < i2 ? i1 : i2;
	}

	/**
	 * Answers the most negative (i.e. closest to negative infinity) of the two
	 * arguments.
	 * 
	 * @param l1
	 *            the first argument to check
	 * @param l2
	 *            the second argument
	 * @return the smaller of l1 and l2.
	 */
	public static long min(long l1, long l2) {
		return l1 < l2 ? l1 : l2;
	}

	/**
	 * Answers the closest double approximation of the result of raising the
	 * first argument to the power of the second.
	 * 
	 * @param d1
	 *            the base of the operation.
	 * @param d2
	 *            the exponent of the operation.
	 * @return d1 to the power of d2
	 */
	public static native double pow(double d1, double d2);

	/**
	 * Answers the double conversion of the result of rounding the argument to
	 * an integer.
	 * 
	 * @param d
	 *            the value to be converted
	 * @return the closest integer to the argument (as a double).
	 */
	public static native double rint(double d);

	/**
	 * Answers the result of rounding the argument to an integer.
	 * 
	 * @param d
	 *            the value to be converted
	 * @return the closest integer to the argument.
	 */
	public static long round(double d) {
		// check for NaN
		if (d != d)
			return 0L;
		return (long) floor(d + 0.5d);
	}

	/**
	 * Answers the result of rounding the argument to an integer.
	 * 
	 * @param f
	 *            the value to be converted
	 * @return the closest integer to the argument.
	 */
	public static int round(float f) {
		// check for NaN
		if (f != f)
			return 0;
		return (int) floor(f + 0.5f);
	}
    
    /**
     * Answers the signum function of the argument. If the argument is less than
     * zero, it answers -1.0. If greater than zero, 1.0 is returned. It returns
     * zero if the argument is also zero.
     * 
     * @param d
     *            the value to compute signum function of
     * @return the value of the signum function.
     */
    public static double signum(double d) {
        return StrictMath.signum(d);
    }
    
    /**
     * Answers the signum function of the argument. If the argument is less than
     * zero, it answers -1.0. If greater than zero, 1.0 is returned. It returns
     * zero if the argument is also zero.
     * 
     * @param f
     *            the value to compute signum function of
     * @return the value of the signum function.
     */
    public static float signum(float f) {
        return StrictMath.signum(f);
    }

	/**
	 * Answers the closest double approximation of the sine of the argument
	 * 
	 * @param d
	 *            the value to compute sin of
	 * @return the sine of the argument.
	 */
	public static native double sin(double d);
    
    /**
     * Answers the closest double approximation of the hyperbolic sine of the
     * argument. The final result should be within 2.5ulps of the real result.
     * 
     * @param d
     *            the value to compute hyperbolic sine of
     * @return the hyperbolic sine of the argument.
     */
    public static native double sinh(double d);

	/**
	 * Answers the closest double approximation of the square root of the
	 * argument
	 * 
	 * @param d
	 *            the value to compute sqrt of
	 * @return the square root of the argument.
	 */
	public static native double sqrt(double d);

	/**
	 * Answers the closest double approximation of the tangent of the argument
	 * 
	 * @param d
	 *            the value to compute tan of
	 * @return the tangent of the argument.
	 */
	public static native double tan(double d);
    
    /**
     * Answers the closest double approximation of the hyperbolic tangent of the
     * argument. The absolute value is always less than 1. The final result
     * should be within 2.5ulps of the real result. If the real result is 
     * within 0.5ulp of 1 or -1, it should answer exactly +1 or -1.
     * 
     * @param d
     *            the value to compute hyperbolic tangent of
     * @return the hyperbolic tangent of the argument.
     */
    public static native double tanh(double d);

	/**
	 * Returns a pseudo-random number between 0.0 and 1.0.
	 * 
	 * @return a pseudo-random number
	 */
	public static double random() {
		if (random == null) {
			random = new java.util.Random();
		}
		return random.nextDouble();
	}

	/**
	 * Returns the measure in radians of the supplied degree angle
	 * 
	 * @param angdeg
	 *            an angle in degrees
	 * @return the radian measure of the angle.
	 */
	public static double toRadians(double angdeg) {
		return angdeg / 180d * PI;
	}

	/**
	 * Returns the measure in degrees of the supplied radian angle
	 * 
	 * @param angrad
	 *            an angle in radians
	 * @return the degree measure of the angle.
	 */
	public static double toDegrees(double angrad) {
		return angrad * 180d / PI;
	}
	
	/**
     * Answers the argument's ulp. The size of a ulp of a double value is the
     * positive distance between this value and the double value next larger
     * in magnitude. For non-NaN x, ulp(-x) == ulp(x).
     * 
     * @param d
     *            the floating-point value to compute ulp of
     * @return the size of a ulp of the argument.
     */
    public static double ulp(double d) {
        // special cases
        if (Double.isInfinite(d)) {
            return Double.POSITIVE_INFINITY;
        } else if (d == Double.MAX_VALUE || d == -Double.MAX_VALUE) {
            return pow(2, 971);
        }
        d = abs(d);
        return nextafter(d, Double.MAX_VALUE) - d;
    }

    /**
     * Answers the argument's ulp. The size of a ulp of a float value is the
     * positive distance between this value and the float value next larger
     * in magnitude. For non-NaN x, ulp(-x) == ulp(x).
     * 
     * @param f
     *            the floating-point value to compute ulp of
     * @return the size of a ulp of the argument.
     */
    public static float ulp(float f) {
        // special cases
        if (Float.isNaN(f)) {
            return Float.NaN;
        } else if (Float.isInfinite(f)) {
            return Float.POSITIVE_INFINITY;
        } else if (f == Float.MAX_VALUE || f == -Float.MAX_VALUE) {
            return (float) pow(2, 104);
        }
        f = abs(f);
        return nextafterf(f, Float.MAX_VALUE) - f;
    }

    private native static double nextafter(double x, double y);

    private native static float nextafterf(float x, float y); 
    
    /**
     * Answers a result of the magnitude of the first given double value and the
     * sign of the second given double value.
     * 
     * @param magnitude
     *            the double value whose magnitude should be used
     * @param sign
     *            the double value whose sign should be used
     * @return a result of the magnitude of the first given double value and the
     *         sign of the second given double value .
     * 
     * @since 1.6
     */
    public static double copySign(double magnitude, double sign) {
        long mbits = Double.doubleToRawLongBits(magnitude);
        long sbits = Double.doubleToRawLongBits(sign);
        return Double.longBitsToDouble((mbits & ~DOUBLE_SIGN_MASK)
                | (sbits & DOUBLE_SIGN_MASK));
    }

    /**
     * Answers a result of the magnitude of the first given float value and the
     * sign of the second given float value .
     * 
     * @param magnitude
     *            the float value whose magnitude should be used
     * @param sign
     *            the float value whose sign should be used
     * @return a result with the magnitude of the first given float value and
     *         the sign of the second given float value .
     * 
     * @since 1.6
     */
    public static float copySign(float magnitude, float sign) {
        int mbits = Float.floatToRawIntBits(magnitude);
        int sbits = Float.floatToRawIntBits(sign);
        return Float.intBitsToFloat((mbits & ~FLOAT_SIGN_MASK)
                | (sbits & FLOAT_SIGN_MASK));
    }
    
    /**
     * Answers the exponent of a float.
     * 
     * @param f
     *            the given float
     * @return the exponent of the float.
     * 
     * @since 1.6
     */
    public static int getExponent(float f) {
        int bits = Float.floatToRawIntBits(f);
        bits = (bits & FLOAT_EXPONENT_MASK) >> FLOAT_MANTISSA_BITS;
        return bits - FLOAT_EXPONENT_BIAS;
    }

    /**
     * Answers the exponent of a double.
     * 
     * @param d
     *            the given double
     * @return the exponent of the double.
     * 
     * @since 1.6
     */
    public static int getExponent(double d) {
        long bits = Double.doubleToRawLongBits(d);
        bits = (bits & DOUBLE_EXPONENT_MASK) >> DOUBLE_MANTISSA_BITS;
        return (int) bits - DOUBLE_EXPONENT_BIAS;
    }    
    
    /**
     * Answers a double next to the first given double value in the direction of
     * the second given double.
     * 
     * @param start
     *            the double value to start
     * @param direction
     *            the double indicating the direction
     * @return a double next to the first given double value in the direction of
     *         the second given double.
     * 
     * @since 1.6
     */
    public static double nextAfter(double start, double direction) {
        if (0 == start && 0 == direction) {
            return direction;
        }
        return nextafter(start, direction);
    }

    /**
     * Answers a float next to the first given float value in the direction of
     * the second given double value.
     * 
     * @param start
     *            the float value to start
     * @param direction
     *            the double indicating the direction
     * @return a float next to the first given float value in the direction of
     *         the second given double.
     * 
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public static float nextAfter(float start, double direction) {
        if (Float.isNaN(start) || Double.isNaN(direction)) {
            return Float.NaN;
        }
        if (0 == start && 0 == direction) {       	
            return new Float(direction);
        }
        if ((start == Float.MIN_VALUE && direction < start)
                || (start == -Float.MIN_VALUE && direction > start)) {
            return (start > 0 ? 0f : -0f);
        }
        if (Float.isInfinite(start) && (direction != start)) {
            return (start > 0 ? Float.MAX_VALUE : -Float.MAX_VALUE);
        }
        if ((start == Float.MAX_VALUE && direction > start)
                || (start == -Float.MAX_VALUE && direction < start)) {
            return (start > 0 ? Float.POSITIVE_INFINITY
                    : Float.NEGATIVE_INFINITY);
        }
        if (direction > start) {
            if (start > 0) {
                return Float.intBitsToFloat(Float.floatToIntBits(start) + 1);
            }
            if (start < 0) {
                return Float.intBitsToFloat(Float.floatToIntBits(start) - 1);
            }
            return +Float.MIN_VALUE;
        }
        if (direction < start) {
            if (start > 0) {
                return Float.intBitsToFloat(Float.floatToIntBits(start) - 1);
            }
            if (start < 0) {
                return Float.intBitsToFloat(Float.floatToIntBits(start) + 1);
            }
            return -Float.MIN_VALUE;
        }
        return new Float(direction);
    }
    
    /**
     * Answers the next larger double value to d.
     * 
     * @param d
     *            the double value to start
     * @return the next larger double value of d.
     * 
     * @since 1.6
     */
    public static double nextUp(double d) {
        if (Double.isNaN(d)) {
            return Double.NaN;
        }
        if ((d == Double.POSITIVE_INFINITY)) {
            return Double.POSITIVE_INFINITY;
        }
        if (0 == d) {
            return Double.MIN_VALUE;
        } else if (0 < d) {
            return Double.longBitsToDouble(Double.doubleToLongBits(d) + 1);
        } else {
            return Double.longBitsToDouble(Double.doubleToLongBits(d) - 1);
        }
    }

    /**
     * Answers the next larger float value to d.
     * 
     * @param f
     *            the float value to start
     * @return the next larger float value of d.
     * 
     * @since 1.6
     */
    public static float nextUp(float f) {
        if (Float.isNaN(f)) {
            return Float.NaN;
        }
        if ((f == Float.POSITIVE_INFINITY)) {
            return Float.POSITIVE_INFINITY;
        }
        if (0 == f) {
            return Float.MIN_VALUE;
        } else if (0 < f) {
            return Float.intBitsToFloat(Float.floatToIntBits(f) + 1);
        } else {
            return Float.intBitsToFloat(Float.floatToIntBits(f) - 1);
        }
    }
    
    /**
     * Answers a double value of d * 2^scaleFactor, the result may be rounded.
     * 
     * @param d
     *            the base number
     * @param scaleFactor
     *            the power number
     * @return d * 2^scaleFactor
     * 
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public static double scalb(double d, int scaleFactor) {
        if (Double.isNaN(d) || Double.isInfinite(d) || 0 == d) {
            return d;
        }
        // change double to long for calculation
        long bits = Double.doubleToLongBits(d);
        // the sign of the results must be the same of given d
        long sign = bits & DOUBLE_SIGN_MASK;
        // calculates the factor of the result
        long factor = ((bits & DOUBLE_EXPONENT_MASK) >> DOUBLE_MANTISSA_BITS)
                - DOUBLE_EXPONENT_BIAS + scaleFactor;

        // calcutes the factor of sub-normal values
        int subNormalFactor = Long.numberOfLeadingZeros(bits
                & ~DOUBLE_SIGN_MASK)
                - DOUBLE_NON_MANTISSA_BITS;
        if (subNormalFactor < 0) {
            // not sub-normal values
            subNormalFactor = 0;
        } else {
            factor = factor - subNormalFactor;
        }
        if (factor > Double.MAX_EXPONENT) {
            return (d > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
        }

        long result;
        // if result is a sub-normal
        if (factor <= -DOUBLE_EXPONENT_BIAS) {
            // the number of digits that shifts
            long digits = factor + DOUBLE_EXPONENT_BIAS + subNormalFactor;
            if (Math.abs(d) < Double.MIN_NORMAL) {
                // origin d is already sub-normal
                result = shiftLongBits(bits & DOUBLE_MANTISSA_MASK, digits);
            } else {
                // origin d is not sub-normal, change mantissa to sub-normal
                result = shiftLongBits(bits & DOUBLE_MANTISSA_MASK
                        | 0x0010000000000000L, digits - 1);
            }
        } else {
            if (Math.abs(d) >= Double.MIN_NORMAL) {
                // common situation
                result = ((factor + DOUBLE_EXPONENT_BIAS) << DOUBLE_MANTISSA_BITS)
                        | (bits & DOUBLE_MANTISSA_MASK);
            } else {
                // origin d is sub-normal, change mantissa to normal style
                result = ((factor + DOUBLE_EXPONENT_BIAS) << DOUBLE_MANTISSA_BITS)
                        | ((bits << (subNormalFactor + 1)) & DOUBLE_MANTISSA_MASK);
            }
        }
        return Double.longBitsToDouble(result | sign);
    }

    /**
     * Answers a float value of d * 2^scaleFactor, the result may be rounded.
     * 
     * @param d
     *            the base number
     * @param scaleFactor
     *            the power number
     * @return d * 2^scaleFactor
     * 
     * @since 1.6
     */
    public static float scalb(float d, int scaleFactor) {
        if (Float.isNaN(d) || Float.isInfinite(d) || 0 == d) {
            return d;
        }
        int bits = Float.floatToIntBits(d);
        int sign = bits & FLOAT_SIGN_MASK;
        int factor = ((bits & FLOAT_EXPONENT_MASK) >> FLOAT_MANTISSA_BITS)
                - FLOAT_EXPONENT_BIAS + scaleFactor;
        // calcutes the factor of sub-normal values
        int subNormalFactor = Integer.numberOfLeadingZeros(bits
                & ~FLOAT_SIGN_MASK)
                - FLOAT_NON_MANTISSA_BITS;
        if (subNormalFactor < 0) {
            // not sub-normal values
            subNormalFactor = 0;
        } else {
            factor = factor - subNormalFactor;
        }
        if (factor > Float.MAX_EXPONENT) {
            return (d > 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY);
        }

        int result;
        // if result is a sub-normal
        if (factor <= -FLOAT_EXPONENT_BIAS) {
            // the number of digits that shifts
            int digits = factor + FLOAT_EXPONENT_BIAS + subNormalFactor;
            if (Math.abs(d) < Float.MIN_NORMAL) {
                // origin d is already sub-normal
                result = shiftIntBits(bits & FLOAT_MANTISSA_MASK, digits);
            } else {
                // origin d is not sub-normal, change mantissa to sub-normal
                result = shiftIntBits(bits & FLOAT_MANTISSA_MASK | 0x00800000,
                        digits - 1);
            }
        } else {
            if (Math.abs(d) >= Float.MIN_NORMAL) {
                // common situation
                result = ((factor + FLOAT_EXPONENT_BIAS) << FLOAT_MANTISSA_BITS)
                        | (bits & FLOAT_MANTISSA_MASK);
            } else {
                // origin d is sub-normal, change mantissa to normal style
                result = ((factor + FLOAT_EXPONENT_BIAS) << FLOAT_MANTISSA_BITS)
                        | ((bits << (subNormalFactor + 1)) & FLOAT_MANTISSA_MASK);
            }
        }
        return Float.intBitsToFloat(result | sign);
    }

    // Shifts integer bits as float, if the digits is positive, left-shift; if
    // not, shift to right and calculate its carry.
    private static int shiftIntBits(int bits, int digits) {
        if (digits > 0) {
            return bits << digits;
        }
        // change it to positive
        int absdigits = -digits;
        if (!(Integer.numberOfLeadingZeros(bits & ~FLOAT_SIGN_MASK) <= (32 - absdigits))) {
            return 0;
        }
        int ret = bits >> absdigits;
        boolean halfbit = ((bits >> (absdigits - 1)) & 0x1) == 1;
        if (halfbit) {
            if (Integer.numberOfTrailingZeros(bits) < (absdigits - 1)) {
                ret = ret + 1;
            }
            if (Integer.numberOfTrailingZeros(bits) == (absdigits - 1)) {
                if ((ret & 0x1) == 1) {
                    ret = ret + 1;
                }
            }
        }
        return ret;
    }

    // Shifts long bits as double, if the digits is positive, left-shift; if
    // not, shift to right and calculate its carry.
    private static long shiftLongBits(long bits, long digits) {
        if (digits > 0) {
            return bits << digits;
        }
        // change it to positive
        long absdigits = -digits;
        if (!(Long.numberOfLeadingZeros(bits & ~DOUBLE_SIGN_MASK) <= (64 - absdigits))) {
            return 0;
        }
        long ret = bits >> absdigits;
        boolean halfbit = ((bits >> (absdigits - 1)) & 0x1) == 1;
        if (halfbit) {
            // some bits will remain after shifting, calculates its carry
            // subnormal
            if (Long.numberOfTrailingZeros(bits) < (absdigits - 1)) {
                ret = ret + 1;
            }
            if (Long.numberOfTrailingZeros(bits) == (absdigits - 1)) {
                if ((ret & 0x1) == 1) {
                    ret = ret + 1;
                }
            }
        }
        return ret;
    }
}
