#include<stdio.h>
#include<time.h>
void strassensmultiply(int row,int A[row][row],int B[row][row],int C[row][row])
{
	int rows=row/2;
	if(row>2)
	{
	int temp1a[row/2][row/2],temp2a[row/2][row/2],temp3a[row/2][row/2],temp4a[row/2][row/2];
	int temp1b[row/2][row/2],temp2b[row/2][row/2],temp3b[row/2][row/2],temp4b[row/2][row/2];
	int i,j;
	int m1[row/2][row/2],m2[row/2][row/2],m3[row/2][row/2],m4[row/2][row/2],m5[row/2][row/2],m6[row/2][row/2],m7[row/2][row/2];
	int c1[row/2][row/2],c2[row/2][row/2],c3[row/2][row/2],c4[row/2][row/2];
	int h=0,k=0;
//	printf("\ntemp1a\n");
	for(i=0;i<(row/2);i++)
	{
		for(j=0;j<(row/2);j++)
		{
			temp1a[h][k]=A[i][j];
	//		printf("%d ",temp1a[h][k] );
			k++;
		}
	//	printf("\n");
		h++;k=0;
	}
	h=0;k=0;
//	printf("\ntemp1b\n");
	for(i=0;i<(row/2);i++)
	{
		for(j=0;j<(row/2);j++)
		{
			temp1b[h][k]=B[i][j];
//			printf("%d ",temp1b[h][k] );
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
//	printf("\ntemp2a\n");
		for(i=0;i<(row/2);i++)
	{
		for(j=(row/2);j<row;j++)
		{
			temp2a[h][k]=A[i][j];
	//		printf("%d ",temp2a[h][k] );
			k++;
		}
	//	printf("\n");
		h++;k=0;
	}
	h=0;k=0;
//	printf("\ntemp2b\n");
	for(i=0;i<(row/2);i++)
	{
		for(j=(row/2);j<row;j++)
		{
			temp2b[h][k]=B[i][j];
//			printf("%d ",temp2b[h][k] );
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
//	printf("\ntemp3a\n");
	for(i=(row/2);i<row;i++)
	{
		for(j=0;j<(row/2);j++)
		{
			temp3a[h][k]=A[i][j];
//			printf("%d ",temp3a[h][k] );
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
//	printf("\ntemp3b\n");
	for(i=(row/2);i<row;i++)
	{
		for(j=0;j<(row/2);j++)
		{
			temp3b[h][k]=B[i][j];
//			printf("%d ",temp3b[h][k] );
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
//	printf("\ntemp4a\n");
	for(i=(row/2);i<row;i++)
	{
		for(j=(row/2);j<row;j++)
		{
			temp4a[h][k]=A[i][j];
//			printf("%d ",temp4a[h][k] );
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
//	printf("\ntemp4b\n");
	for(i=(row/2);i<row;i++)
	{
		for(j=(row/2);j<row;j++)
		{
			temp4b[h][k]=B[i][j];
//			printf("%d ",temp4b[h][k] );
			k++;
		}
//		printf("\n");
	h++;k=0;
	}
	int tempa[row/2][row/2];
	int tempb[row/2][row/2];
	for(i=0;i<row/2;i++)
	{
		for(j=0;j<row/2;j++)
		{
			tempa[i][j]=temp1a[i][j]+temp4a[i][j];
			tempb[i][j]=temp1b[i][j]+temp4b[i][j];
		}
	}
	strassensmultiply((row/2),tempa,tempb,m1);
//	printf("Result of m1 is as follows\n");
	/*for(i=0;i<row/2;i++)
	{	
		j=0;
		for(j=0;j<row/2;j++)
		{
//			printf("%d ",m1[i][j]);
		}
//		printf("\n");
	}*/
	for(i=0;i<row/2;i++)
	{
		for(j=0;j<row/2;j++)
		{
			tempa[i][j]=temp3a[i][j]+temp4a[i][j];
			tempb[i][j]=temp1b[i][j];
		}
	}
	strassensmultiply((row/2),tempa,tempb,m2);
	/*printf("Result of m2 is as follows\n");
	for(i=0;i<row/2;i++)
	{	
		j=0;
		for(j=0;j<row/2;j++)
		{
			printf("%d ",m2[i][j]);
		}
		printf("\n");
	}
*/
	for(i=0;i<row/2;i++)
	{
		for(j=0;j<row/2;j++)
		{
			tempa[i][j]=temp1a[i][j];
			tempb[i][j]=temp2b[i][j]-temp4b[i][j];
		}
	}
	strassensmultiply((row/2),tempa,tempb,m3);
/*	printf("Result of m3 is as follows\n");
	for(i=0;i<row/2;i++)
	{	
		j=0;
		for(j=0;j<row/2;j++)
		{
			printf("%d ",m3[i][j]);
		}
		printf("\n");
	}
*/
	for(i=0;i<row/2;i++)
	{
		for(j=0;j<row/2;j++)
		{
			tempa[i][j]=temp4a[i][j];
			tempb[i][j]=temp3b[i][j]-temp1b[i][j];
		}
	}
	strassensmultiply((row/2),tempa,tempb,m4);
/*	printf("Result of m4 is as follows\n");
	for(i=0;i<row/2;i++)
	{	
		j=0;
		for(j=0;j<row/2;j++)
		{
			printf("%d ",m4[i][j]);
		}
		printf("\n");
	}
*/
	for(i=0;i<row/2;i++)
	{
		for(j=0;j<row/2;j++)
		{
			tempa[i][j]=temp1a[i][j]+temp2a[i][j];
			tempb[i][j]=temp4b[i][j];
		}
	}
	strassensmultiply((row/2),tempa,tempb,m5);
/*	printf("Result of m5 is as follows\n");
	for(i=0;i<row/2;i++)
	{	
		j=0;
		for(j=0;j<row/2;j++)
		{
			printf("%d ",m5[i][j]);
		}
		printf("\n");
	}
*/
	for(i=0;i<row/2;i++)
	{
		for(j=0;j<row/2;j++)
		{
			tempa[i][j]=temp3a[i][j]-temp1a[i][j];
			tempb[i][j]=temp1b[i][j]+temp2b[i][j];
		}
	}
	strassensmultiply((row/2),tempa,tempb,m6);
/*	printf("Result of m6 is as follows\n");
	for(i=0;i<row/2;i++)
	{	
		j=0;
		for(j=0;j<row/2;j++)
		{
			printf("%d ",m6[i][j]);
		}
		printf("\n");
	}
*/
	for(i=0;i<row/2;i++)
	{
		for(j=0;j<row/2;j++)
		{
			tempa[i][j]=temp2a[i][j]-temp4a[i][j];
			tempb[i][j]=temp3b[i][j]+temp4b[i][j];
		}
	}
	strassensmultiply((row/2),tempa,tempb,m7);
/*	printf("Result of m7 is as follows\n");
	for(i=0;i<row/2;i++)
	{	
		j=0;
		for(j=0;j<row/2;j++)
		{
			printf("%d ",m7[i][j]);
		}
		printf("\n");
	}
*/
	for(i=0;i<row/2;i++)
	{
		for(j=0;j<row/2;j++)
		{
			c1[i][j]=m1[i][j]+m4[i][j]-m5[i][j]+m7[i][j];
			c2[i][j]=m3[i][j]+m5[i][j];
			c3[i][j]=m2[i][j]+m4[i][j];
			c4[i][j]=m1[i][j]+m3[i][j]-m2[i][j]+m6[i][j];
		}
	}

	h=0;k=0;
	for(i=0;i<(row/2);i++)
	{
		for(j=0;j<(row/2);j++)
		{
			C[i][j]=c1[h][k];
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
	for(i=0;i<(row/2);i++)
	{
		for(j=(row/2);j<row;j++)
		{
			C[i][j]=c2[h][k];
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
	for(i=(row/2);i<row;i++)
	{
		for(j=0;j<(row/2);j++)
		{
			C[i][j]=c3[h][k];
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
	for(i=(row/2);i<row;i++)
	{
		for(j=(row/2);j<row;j++)
		{
			C[i][j]=c4[h][k];
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
	}
	else if(row==2)
	{
		int m1,m2,m3,m4,m5,m6,m7,tempi=0,tempj=0,i,j;
		while(row>(tempj+1))
		{
		while(row>(tempi+1))
		{
			m1=((A[tempi][tempj]+A[tempi+1][tempj+1])*(B[tempi][tempj]+B[tempi+1][tempj+1]));
			m2=(A[tempi+1][tempj]+A[tempi+1][tempj+1])*B[tempi][tempj];
			m3=A[tempi][tempj]*(B[tempi][tempj+1]-B[tempi+1][tempj+1]);
			m4=A[tempi+1][tempj+1]*(B[tempi+1][tempj]-B[tempi][tempj]);
			m5=(A[tempi][tempj]+A[tempi][tempj+1])*B[tempi+1][tempj+1];
			m6=(A[tempi+1][tempj]-A[tempi][tempj])*(B[tempi][tempj]+B[tempi][tempj+1]);
			m7=(A[tempi][tempj+1]-A[tempi+1][tempj+1])*(B[tempi+1][tempj]+B[tempi+1][tempj+1]);

			C[tempi][tempj]=m1+m4-m5+m7;
			C[tempi][tempj+1]=m3+m5;
			C[tempi+1][tempj]=m2+m4;
			C[tempi+1][tempj+1]=m1+m3-m2+m6;
			tempi=tempi+2;
		}
		tempi=0;
		tempj=tempj+2;
	}
/*	printf("Result of intermediate matrix multiplication is as follows\n");
	for(i=0;i<row;i++)
	{	
		j=0;
		for(j=0;j<row;j++)
		{
			printf("%d ",C[i][j]);
		}
		printf("\n");
	}
		*/
	}
}
int main()
{
	int row,column,i,j,tempi,tempj,k;
	int m1,m2,m3,m4,m5,m6,m7;
	printf("Enter the number of rows(=number of columns) in the matrix\n");
	scanf("%d",&row);
	column=row;
	int A[row][column];
	int B[row][column];
	int C[row][column];
	int D[row][column];
	srand(time(NULL));
	printf("Enter the elements, row wise in the first matrixn\n");
	for(i=0;i<row;i++)
	{	
		j=0;
		for(j=0;j<column;j++)
		{
			A[i][j]=(rand()%20-10);
		}
	}
	printf("Enter the elements, row wise in the second matrix\n");
	for(i=0;i<row;i++)
	{	
		j=0;
		for(j=0;j<column;j++)
		{
			B[i][j]=(rand()%20-10);
		}
	}
	printf("Elements in the first matrix are as follows \n");
	for(i=0;i<row;i++)
	{	
		j=0;
		for(j=0;j<column;j++)
		{
			printf("%d ",A[i][j]);
		}
		printf("\n");
	}
	printf("Elements in the second matrix are as follows\n");
	for(i=0;i<row;i++)
	{	
		j=0;
		for(j=0;j<column;j++)
		{
			printf("%d ",B[i][j]);
		}
		printf("\n");
	}
	int sum;
	printf("Result of normal matrix multiplication is as follows\n");
	for(i=0;i<row;i++)
	{	
		for(j=0;j<column;j++)
		{
			sum=0;
			for(k=0;k<row;k++)
            {   sum=sum+A[i][k]*B[k][j];}
           	   D[i][j]=sum;
           	   printf("%d ",D[i][j]);
		}
		printf("\n");
	}
	strassensmultiply(row,A,B,C);
	printf("Result of matrix multiplication is as follows\n");
	for(i=0;i<row;i++)
	{	
		j=0;
		for(j=0;j<column;j++)
		{
			printf("%d  ",C[i][j]);
		}
		printf("\n");
	}
	return 0;
}