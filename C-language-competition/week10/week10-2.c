#include<stdio.h>
#include<stdlib.h>
int main()
{
	int a[10],fg,k,i,s,n,j,r,p,k1,k2;
	char ch;
	long x,y,z;
	scanf("%c%d%d",&ch,&k1,&k2);
	p=ch-'A'+1;
	i=1;
	s=0;
	a[i]=1;
	while(1)
	{
		fg=1;
		for(k=i-1;k>=1;k--)
			if(a[i]==a[k])
				fg=0;
		if(i==p && (a[p]<k1||a[p]>k2))
			fg=0;
		if(i==9 && fg==1)
		{
			x=a[2]*10+a[3];
			y=a[5]*10+a[6];
			z=a[8]*10+a[9];
			if(a[4]*a[7]*x+a[1]*a[7]*y==a[1]*a[4]*z)
			{
				s++;
				printf("(%d)%ld/%d+%ld/%d=%ld/%d\n",s,x,a[1],y,a[4],z,a[7]);
			}
		}
		if(fg && i<9)
		{
			i++;
			a[i]=1;
			continue;
		}
		while(a[i]==9 && i>1)
			i--;
		if(i==1 && a[i]==9)
			break;
		else
			a[i]++;
	}
	printf("%d\n",s);
	return 0;
}

