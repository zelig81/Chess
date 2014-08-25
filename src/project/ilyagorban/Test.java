package project.ilyagorban;

import project.ilyagorban.model.Rank;

public class Test {

	public static void main(String[] args) {
		for(Rank r: Rank.values()){
			System.out.print(r.getPicture());
		}

	}

}
