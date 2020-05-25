public class Exame{

	private String name;
	private String code;
	private String obs;
	private String duration;
	private String timeResult;

	Exame(String name, String code, String obs, String duration, String timeR){
		
		setCode(code);
		setName(name);
		setObs(obs);
		setDuration(duration);
		setEstResult(timeR);
	
	}
	Exame(){
		this.name = "";
		this.code = "";
		this.obs = "";
		this.duration = "";
		this.timeResult = "";
	}

	public String getName(){

		return this.name;

	}

	public int setName(String name){
		
		if(name.length() > 0){
			this.name = name;
			return -1;

		}
		return 2;
	}

	public String getCode(){

		return this.code;

	}
	public int setCode(String code){
		
		if(code.length() > 0){
			this.code = code;
			return -1;

		}
		return 2;
	}

	public String getObs(){

		return this.obs;

	}
	public int setObs(String obs){
		
		if(obs.length() > 0){
			this.obs = obs;
			return -1;

		}
		return 2;
	}	

	public String getDuration(){

		return this.duration;

	}
	public int setDuration(String dur){
		
		if(dur.length() > 0){
			this.duration = dur;
			return -1;

		}
		return 2;
	}

	public String estResult(){

		return this.timeResult;
	}
	public int setEstResult(String etr){
		
		if(etr.length() > 0){
			this.timeResult = etr;	
			return -1;

		}
		return 2;
	}
}

