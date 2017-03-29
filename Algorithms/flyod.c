#include <stdio.h>
#include <string.h>
#include <stdlib.h>
void print(int v,int A[v][v])
{
	int i,j;
	for(i=0;i<v;i++)
	{
		for(j=0;j<v;j++)
		{
			if(A[i][j]!=499&&i!=j)
			{
				printf("Distance of vertex %d from vertex %d is %d\n",(j+1),(i+1),A[i][j]);
			}
		}
	}
}
void floyd(int v,int row,int adjacency[row][row])
{
	int A[v][v],i,j,k;
	for(i=0;i<v;i++)
	{
		for(j=0;j<v;j++)
		{
			A[i][j]=adjacency[i][j];
		}
	}
	for(k=0;k<v;k++)
	{
		for(i=0;i<v;i++)
		{
			for(j=0;j<v;j++)
			{
				if(A[i][k]+A[k][j]<A[i][j])
				{
					A[i][j]=A[i][k]+A[k][j];
				}
			}
		}
	}
	print(v,A);
	printf("\n");
}
int main()
{
	FILE * fp=fopen("input.txt","r");
	int i,j;
	int adjacency[100][100];
	i=0;j=0;
	if(fp==NULL)
	{
		printf("Unable to open file");
		return 0;
	}
	else 
	{

			char *token;
			char temp[1000];
			int k=0;
			while(!feof(fp))
			{
						fgets(temp,1000,fp);
						token=strtok(temp,",");
						while(token!=NULL)
						{
							
							adjacency[i][j]= atoi(token);
							j++;
							token=strtok(NULL,",");
	
						}
						strcpy(temp,"");
						i++;j=0;
			}
	
		fclose(fp);
	} 
	int v=(i-1);	
	floyd(v,100,adjacency);
	return 0;
}