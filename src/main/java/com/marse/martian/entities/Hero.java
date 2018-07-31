package com.marse.martian.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Hero {

	private static final Set<Hero> heros;
	private static Random random;

	static {
		heros = new HashSet<>();
		buildDummyData(heros);
		random = new Random();
	}

	private int id;

	private String name;

	public Hero() {

	}

	public Hero(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static void buildDummyData(Set<Hero> heros) {
		heros.add(new Hero(1, "Shubham"));
		heros.add(new Hero(2, "Nilesh"));
		heros.add(new Hero(3, "Shivani"));
		heros.add(new Hero(4, "Matt"));
		heros.add(new Hero(5, "Kelsey"));
		heros.add(new Hero(6, "Myce"));
	}

	public static Set<Hero> getAllHeros() {
		return heros;
	}

	public static Hero getHero(int id) {
		return heros.stream().filter(hero -> hero.id == id).findFirst().orElse(null);
	}

	public static Hero updateHero(Hero hero) {
		heros.stream().filter(h -> h.id == hero.id).findFirst().get().setName(hero.getName());
		return hero;
	}

	public static Hero saveHero(Hero hero) {
		hero.setId(random.nextInt(100));
		heros.add(hero);
		return hero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Hero other = (Hero) obj;
		return Objects.equals(other.id, this.id) && Objects.equals(other.name, this.name);
	}

	@Override
	public String toString() {
		return "Heros [id=" + id + ", name=" + name + "]";
	}

	public static boolean deleteHero(Integer id) {
		boolean flag = heros.remove(heros.stream().filter(h -> h.id == id).findFirst().get());
		return flag;
	}

	public static Set<Hero> searchHeros(String name) {
		return heros.stream().filter(h -> h.name.toLowerCase().startsWith(name.toLowerCase())).collect(Collectors.toSet());
	}

}
