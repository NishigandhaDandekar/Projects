#include <stdio.h>
#include <time.h>
static int id1=1;
void print(int row,int A[row][row])
{
	int i,j,h,k;
	for(i=0;i<row;i++)
	{
		for(j=0;j<row;j++)
		{
			if(A[i][j]!=-1)
				printf("%d\t",A[i][j]);	
			else 
				printf("X\t");
		} 
		printf("\n");
	}
}
void tile(int k,int row,int A[row][row],int xm,int ym)
{
	int i,j;
//	printf("xm : %d ym : %d row : %d\n",xm,ym,row);
	int flag1=0,flag2=0,flag3=0,flag4=0; //flag1 is 1 if there is an empty spot in the first quandrant
	
	if(k==1||row==2)
	{
		for(i=0;i<row;i++)
		{
			for(j=0;j<row;j++)
			{
				if(A[i][j]==0)
				{
					A[i][j]=id1;
				}
			}
		}
		id1=id1+1;
	}
	else 
	{
		int h,k;	
		int temp1a[row/2][row/2],temp2a[row/2][row/2],temp3a[row/2][row/2],temp4a[row/2][row/2];
		h=0;k=0;
	for(i=0;i<(row/2);i++)
	{
		for(j=0;j<(row/2);j++)
		{
			temp1a[h][k]=A[i][j];
		//	printf("%d ",temp1a[h][k] );
			if(temp1a[h][k]==-1||temp1a[h][k]!=0)
			{	flag1=1;
				xm=h;
				ym=k;
			}	
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
			temp2a[h][k]=A[i][j];
		//	printf("%d ",temp2a[h][k] );
			if(temp2a[h][k]==-1||temp2a[h][k]!=0)
			{	flag2=1;
				xm=h;
				ym=k;
			}	
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
			temp3a[h][k]=A[i][j];
//			printf("%d ",temp3a[h][k] );
			if(temp3a[h][k]==-1||temp3a[h][k]!=0)
			{	flag3=1;
				xm=h;
				ym=k;
			}	
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
			temp4a[h][k]=A[i][j];
//			printf("%d ",temp4a[h][k] );
			if(temp4a[h][k]==-1||temp4a[h][k]!=0)
			{	flag4=1;
				xm=h;
				ym=k;
			}	
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
	
	if(flag1==1)
	{
		temp2a[(row/2)-1][0]=id1;
		temp3a[0][(row/2)-1]=id1;
		temp4a[0][0]=id1;
		id1=id1+1;
		tile((k-1),row/2,temp1a,xm,ym);
		tile((k-1),row/2,temp2a,((row/2)-1),0);
		tile((k-1),row/2,temp3a,0,((row/2)-1));
		tile((k-1),row/2,temp4a,0,0);
	}
	else if(flag2==1)
	{
		temp1a[(row/2)-1][(row/2)-1]=id1;
		temp3a[0][(row/2)-1]=id1;
		temp4a[0][0]=id1;
		id1=id1+1;
		tile((k-1),row/2,temp1a,((row/2)-1),((row/2)-1));
		tile((k-1),row/2,temp2a,xm,ym);
		tile((k-1),row/2,temp3a,0,((row/2)-1));
		tile((k-1),row/2,temp4a,0,0);
		
	}
	else if(flag3==1)
	{
		temp2a[(row/2)-1][0]=id1;
		temp1a[(row/2)-1][(row/2)-1]=id1;
		temp4a[0][0]=id1;
		id1=id1+1;
		tile((k-1),row/2,temp1a,((row/2)-1),((row/2)-1));
		tile((k-1),row/2,temp2a,((row/2)-1),0);
		tile((k-1),row/2,temp3a,xm,ym);
		tile((k-1),row/2,temp4a,0,0);
		
	}
	else if(flag4==1)
	{
		temp2a[(row/2)-1][0]=id1;
		temp3a[0][(row/2)-1]=id1;
		temp1a[(row/2)-1][(row/2)-1]=id1;
		id1=id1+1;
		tile((k-1),row/2,temp1a,((row/2)-1),((row/2)-1));
		tile((k-1),row/2,temp2a,((row/2)-1),0);
		tile((k-1),row/2,temp3a,0,((row/2)-1));
		tile((k-1),row/2,temp4a,xm,ym);
		
	}
	h=0;k=0;
	for(i=0;i<(row/2);i++)
	{
		for(j=0;j<(row/2);j++)
		{
			A[i][j]=temp1a[h][k];
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
			A[i][j]=temp2a[h][k];
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
			A[i][j]=temp3a[h][k];
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
			A[i][j]=temp4a[h][k];
			k++;
		}
//		printf("\n");
		h++;k=0;
	}
	h=0;k=0;
}
	
}

int main()
{
	int k,xm,ym,i,j;
	int row=1;
	srand(time(NULL));
	printf("Enter the value of k\n");
	scanf("%d",&k);
	printf("Value of k is %d\n",k);
	int id;
	for(i=0;i<k;i++)
	{
		row=row*2;
	}
	id=((row*row)-1)/3;
	printf("The size of the matrix is %d\n",row);
	xm=rand() % row;
	ym=rand() % row;
	printf("The coordinates of the X is x: %d y: %d\n",xm,ym);
	int A[row][row];
//	A[xm][ym]=-1;
	for(i=0;i<row;i++)
	{
		for(j=0;j<row;j++)
		{
			if(i==xm && j==ym)
			{
				A[i][j]=-1;
			}
			else 
			{
				A[i][j]=0;
			}
		}
	}
	printf("\n");
	print(row,A);
	tile(k,row,A,xm,ym);
	printf("\n");
	print(row,A);
	return 0;
}