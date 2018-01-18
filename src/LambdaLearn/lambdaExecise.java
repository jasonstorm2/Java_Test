package LambdaLearn;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class lambdaExecise {
	public static void main(String[] args) {
		lambdaExecise thisClass = new lambdaExecise();
		System.out.println("��ͽ����"+thisClass.addUp(Arrays.asList(1,2,3).stream()));
		char a = 'a';//a97
		char b = 70;//A65  Z90
		
		System.out.println(b);
		System.out.println((int)'A');
		
		
		System.out.println(thisClass.getNameNation(new ArrayList<Artist>(){{
			add(new Artist("john", "china"));
			add(new Artist("bell", "canada"));
			}}));
		
		List<String> ls = new ArrayList<String>(){{
			add(new String("aBBB"));add(new String("dddAc"));	
			}};
			
			thisClass.reduceCode();
			
			List<Object> ll =  map(Arrays.asList(1,2,3).stream(), (aa)->{
				return String.valueOf(aa)+"lll";
			});
			System.out.println("��reduceʵ��map���ܣ�"+ll.toString());
			List<Integer> ldl =  filter(Arrays.asList(1,2,3).stream(),(aaa)->aaa>=2);
			System.out.println("��reduceʵ��filter���ܣ�"+ldl.toString());
//			Collection
//			MyCustomList m = new MyCustomList();
			
			Artists ar = new Artists(Stream.of(new Artist("jason", "china")).collect(Collectors.toList()));
			ar.getArtistName(0);
			
			
			/**************������****************/
			//1 a. ת����д��map ������
			List<String> collected2 = Stream.of("a", "b", "hello")
					.map(String::toUpperCase).collect(Collectors.toList());
			//1 b. ʹ��reduce ʵ��count ������
			collected2.stream().reduce((a1,b1)->{
				return a1+b1;
			});
			int va = 0;
			System.out.println(collected2.stream().reduce((a1,b1)->{
				return a1+b1;
			}).get());
			//1 c. ʹ��flatMap �����б�
			
			
			//2 a.�ҳ��������������
			//����1
			Comparator<String> comparator = Comparator.comparing(name->name.length());
			Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
					"George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
			names.max(comparator).get();
			//����2
			Stream<String> names2 = Stream.of("John Lennon", "Paul McCartney",
					"George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
			names2.reduce("",(acc, x) -> {
		        		if(acc.length()<x.length()){		        			
		        			acc=x;
		        			}
		        		return acc;		        	
		            });	
			//����3
			Stream<String> names3 = Stream.of("John Lennon", "Paul McCartney",
					"George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
			names3.collect(Collectors.maxBy(comparator)).get();
	}
	
	//1.����������
	/**
	 * ��ͺ����� ��������������֮��
	 * @param nums
	 * @return
	 */
	public int addUp(Stream<Integer> nums){		
		return nums.reduce(0,(a,b)->a+b);		
	}
	
	/**
	 * �����������б���Ϊ����������һ���ַ����б����а��������ҵ������͹�����
	 * @param str
	 * @return
	 */
	
	public List<String> getNameNation(List<Artist> str){
	;
		System.out.println(	str.stream()
		        .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
		        .collect(toList()));
		return str.stream().map((s)->s.getName()+s.getNationality()).collect(Collectors.toList());		
	}
	
	/**
	 * ����ר���б���Ϊ����������һ����������3 �׸�����ר����ɵ��б�
	 * @param album
	 * @return
	 */
	public List<Album> getAlbumList(List<Album> album){
		return album.stream().filter((a)->a.getTracks().count()>2).collect(toList());		
	}
	
	//2.����
	/**
	 * �������޸����´��룬���ⲿ����ת�����ڲ�������
	 * @param art
	 */
	public int Itera(List<Artist> art){
		Integer total =  0;
		/***foreach����***/
//		art.stream().forEach((a)->{total = (int) (a.getMembers().count()+total);});
		return (int) art.stream().flatMap((a)->a.getMembers()).count();
	}
	
	//3.��ְ
	boolean anyMatch(Predicate<String> predicate){
		String s = null;
		return predicate.test(s);	
	}
	
	//4.�߽׺���
	//�ǣ���Ϊ���ص���һ������
	
	//5.������
	//�и����ã���Ϊʹ�����ⲿ�ı��������ı���Ĭ��Ϊfinalֵ���޷��ı�
	
	
	//6. ����һ���ַ�����Сд��ĸ�ĸ���
	
	public static int getNums(String str){
		return (int) str.chars().filter(Character::isLowerCase).count();
	}
	
	//��һ���ַ����б��У��ҳ��������Сд��ĸ���ַ���
	
	public String getStrShort(List<String> str){
		return str.stream().max(Comparator.comparing(lambdaExecise::getNums)).get();			
	}
	
	public void reduceCode(){
		Stream.of(1,2,3,4).reduce((a,b)->a+10).get();
		System.out.println(Stream.of(1,2,3,4).reduce((a,b)->a+10).get());
	}
	
	/**
	 * ����1.��reduceʵ��map�Ĺ���
	 * @param stream Ҫ�����Stream
	 * @param mapper ��Stream�����Ĵ���
	 * @return
	 */
    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
    	List<String> newssssAcc = new ArrayList<>();
        return stream.reduce(
        		
        	// 1.��ʼֵ
        	new ArrayList<O>(),

        	// 2.����һ��Ԫ��
        	(acc, x) -> {
        	List<O> newAcc = new ArrayList<>(acc);
        	// function ����x����������O
        	newAcc.add(mapper.apply(x));
            return newAcc;
            },
            // 3.Ԫ�غ���һ��Ԫ��ƴװ
            (List<O> left, List<O> right) -> {
        	List<O> newLeft = new ArrayList<>(left);
        	newLeft.addAll(right);
            return newLeft;
        });
    }
    
  
    /**
     * ֻ��reduce ��Lambda ���ʽд��ʵ��Stream �ϵ�filter �����Ĵ��룬������뷵��Stream�����Է���һ��List
     * @param stream
     * @param mapper
     * @return
     */
    public static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {    	
        return stream.reduce(        		
            	// 1.��ʼֵ
            	new ArrayList<I>(),

            	// 2.����һ��Ԫ��
            	(acc, x) -> {
            	List<I> newAcc = new ArrayList<>(acc);
            	// predicate ����x����������һ������ֵ
            	if(predicate.test(x)){
            		newAcc.add(x);
            	}           	
            	
                return newAcc;
                },
                // 3.Ԫ�غ���һ��Ԫ��ƴװ
                (List<I> left, List<I> right) -> {
            	List<I> newLeft = new ArrayList<>(left);
            	newLeft.addAll(right);
                return newLeft;
            });
    	
    }

}

/**
 * 
 * @author Administrator
 * �����ҽṹ��
 */
class Artist {
    
    private String name;
    private List<Artist> members;
    private String nationality;
    
    public Artist(String name, String nationality) {
        this(name, Collections.emptyList(), nationality);
    }

    public Artist(String name, List<Artist> members, String nationality) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(members);
        Objects.requireNonNull(nationality);
        this.name = name;
        this.members = new ArrayList<>(members);
        this.nationality = nationality;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the members
     */
    public Stream<Artist> getMembers() {
        return members.stream();
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    public boolean isSolo() {
        return members.isEmpty();
    }
        
    public static boolean isSolo2(Artist a){
    	return false;
    }
    
    public static boolean isSolo3(Artist a){
    	return false;
    }
    
    public boolean isSolo4(){
    	return false;
    }

    public boolean isFrom(String nationality) {
        return this.nationality.equals(nationality);
    }

    @Override
    public String toString() {
        return getName();
    }

    public Artist copy() {
        List<Artist> members = getMembers().map(Artist::copy).collect(toList());
        return new Artist(name, members, nationality);
    }

}


/**
 * ר���ṹ��
 * @author Administrator
 *
 */
class Album implements Performance{
    private String name;
    private List<Track> tracks;
    private List<Artist> musicians;

    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(tracks);
        Objects.requireNonNull(musicians);

        this.name = name;
        this.tracks = new ArrayList<>(tracks);
        this.musicians = new ArrayList<>(musicians);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    public String getName2(String valu) {
    	name = name + valu;
        return name;
    }


    /**
     * @return the tracks
     */
    public Stream<Track> getTracks() {
        return tracks.stream();
    }

    /**
     * Used in imperative code examples that need to iterate over a list
     */
    public List<Track> getTrackList() {
        return unmodifiableList(tracks);
    }

    /**
     * @return the musicians
     */
    public Stream<Artist> getMusicians() {
        return musicians.stream();
    }

    /**
     * Used in imperative code examples that need to iterate over a list
     */
    public List<Artist> getMusicianList() {
        return unmodifiableList(musicians);
    }

    public Artist getMainMusician() {
        return musicians.get(0);
    }

    public Album copy() {
        List<Track> tracks = getTracks().map(Track::copy).collect(toList());
        List<Artist> musicians = getMusicians().map(Artist::copy).collect(toList());
        return new Album(name, tracks, musicians);
    }
}

class Track { 
    private final String name;
    private final int length;

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }
    public String getName() {
        return name;
    }
    public int getLength() {
        return length;
    }

    public Track copy() {
        return new Track(name, length);
    }
}

interface Performance {

	public String getName();

	public Stream<Artist> getMusicians();

	public default Stream<Artist> getAllMusicians() {
		return getMusicians().flatMap(
				artist -> concat(Stream.of(artist), artist.getMembers()));
	};	
}
class Per implements Performance {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Artist> getMusicians() {
		// TODO Auto-generated method stub
		return null;
	}

}

class Artists {
	private List<Artist> artists;

	public Artists(List<Artist> artists) {
		this.artists = artists;
	}

	public Optional<Artist> getArtist(int index) {
		if (index < 0 || index >= artists.size()) {
			return Optional.empty();
		}
		return Optional.of(artists.get(index));
	}

	
	public String getArtistName(int index) {
		Optional<Artist> artist = getArtist(index);
//		if(artist.isPresent()){
//			return artist.get().getName();
//		}else{
//			return "none";
//		}	
		System.out.println("Optional map �Ľ����");
		System.out.println(artist.map(a->1));
		System.out.println(artist.map(a->getArtist(index+1)));
		// Optional<Artist> ------> Optional<String>
		System.out.println(artist.map(Artist::getName));
		
		
        return artist.map(Artist::getName).orElse("unknown");
	}
}