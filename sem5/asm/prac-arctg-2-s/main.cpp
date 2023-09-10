#include <string.h>
#include <stdio.h>
#include <intrin.h>
                                          
extern "C" float atan_fpu(float x, int n);
extern "C" float atan_sse(float x, int n);
                                
const float tests[9] = {1.0, 0.0, -1.0, 0.75, -0.75, 0.5, -0.5, 0.25, -0.25};
const int T = 1000000;
const long long TIME_M = 1000000;

int main() {                                            
//	printf("fpu= %.10f\n", atan_fpu(1.0f, 1000000));
//	printf("sse= %.10f\n", atan_sse(1.0f, 1000000));

        unsigned int B;
        for (int i = 0; i < 9; ++i) {
                unsigned volatile __int64 Astart1 = __rdtscp(&B);
		printf("fpu= %.10f\n", atan_fpu(tests[i], T));  
                unsigned volatile __int64 Astart2 = __rdtscp(&B);
		printf("sse= %.10f\n", atan_sse(tests[i], T));
                unsigned volatile __int64 Astop2 = __rdtscp(&B);
                printf("*** fpu time=%lld\n*** sse time=%lld\n\n", (Astart2 - Astart1) / TIME_M, (Astop2 - Astart2)  / TIME_M);
	}
}