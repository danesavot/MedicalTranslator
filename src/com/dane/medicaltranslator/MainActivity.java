package com.dane.medicaltranslator;

import java.io.ObjectOutputStream.PutField;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends SherlockFragmentActivity implements
		TabListener {

	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentPagerAdapter contentPagerAdapter = new ContentPagerAdapter(
				getSupportFragmentManager());
		final ActionBar actionBar = getSupportActionBar();

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between different app sections, select
						// the corresponding tab.
						// We can also use ActionBar.Tab#select() to do this if
						// we have a reference to the
						// Tab.
						actionBar.setSelectedNavigationItem(position);
					}

				});
		mViewPager.setAdapter(contentPagerAdapter);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (int i = 0; i < ContentPagerAdapter.contents.length; i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(contentPagerAdapter.getPageTitle(i))
					.setTabListener(this));

		}

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
		mViewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);

	}

	public static class ContentPagerAdapter extends FragmentStatePagerAdapter {

		public static final String[] contents = { "Introduction", "Number",
				"Mental Health", "Chief Complaint", "Body Parts" };

		// public static ArrayList<Object> contents;

		public ContentPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int position) {

			ContentFragment contentFragment = new ContentFragment();
			Bundle args = new Bundle();
			args.putInt(ContentFragment.POSITION, position);
			contentFragment.setArguments(args);
						
			return contentFragment;
		}

		@Override
		public int getCount() {

			return contents.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return contents[position];
		}

	}

	public static class ContentFragment extends SherlockFragment implements OnItemClickListener {
		public static final String POSITION = "position";
		Bundle args;
		int position;
		View layout;
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			HashMap<String, Object> content = new HashMap<String, Object>();
			
			switch (position) {
			case 0:

				
				content
						.put("Greeting", new String[] { "Hello",
								"Do you speak English?", "Good morning!",
								"Good afternoon!", "Good evening",
								"Nice to meet you" });

				content.put("Introduction", new String[] {
						"What is your name?", "How old are you?",
						"What is your date of birth?",
						"How much do you weigh?", "How tall are you?",
						"Are you married or single?", "I am your doctor.",
						"I am your nurse.", "I am a medical student." });

				content
						.put("Farewell",
								new String[] {
										"Thank you for anwsering my questions.",
										"I will come back later.",
										"I will return when I have additional information for you." });
						
				content.put("Miscellaneous", new String[]{"What is your name?"});

								
				break;
			
			case 1:

				content.put("0","0");
				content.put("1","1");
				content.put("2","2");
				content.put("3","3");
				content.put("4","4");
				content.put("5","5");
				content.put("6","6");
				break;
			case 2:
				
				content.put("PTSD",new String[]{"Have you had repeated disturbing memories, thoughts, or images of a stressful experience in the past?","Have you had repeated, disturbing dreams of a stressful experience from the past?"});
				content.put("Mental Health ", new String[]{"Do you feel depressed?"});
				break;
			case 3:
				
				content.put("Male Reproductive",new String[]{"Do you have pain when you urinate?","Do you have any problem with bladder control?"});
				content.put("Orthopedic",new String[]{"Are you experiencing pain in your neck?","Are you experiencing pain shoulder?"});
				break;
			default:
				content.put("Head","Head");
				content.put("Neck","Neck");
				content.put("Ears","Ears");
				content.put("Eyes","Eyes");
				content.put("Throat","Throat");
				content.put("Shoulder","Shoulder");
				content.put("Chest","Chest");
				
				break;

			}

			final SherlockFragmentActivity activity = getSherlockActivity();
			ContentListAdapter contentListAdapter = new ContentListAdapter(
					activity, content.keySet().toArray(
							new String[content.keySet().toArray().length]),content);

			ListView contentListView = (ListView) layout.findViewById(R.id.listView1);
			contentListView.setAdapter(contentListAdapter);
			
			
			contentListView.setOnItemClickListener(this);

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			args = getArguments();
			position = args.getInt(POSITION);
			layout = inflater.inflate(R.layout.content_list, container, false);
			return layout;

		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			Toast.makeText(getSherlockActivity(), "Clicked", Toast.LENGTH_SHORT).show();
			
			ContentFragment contentFragment = new ContentFragment();
			Bundle args = new Bundle();
			args.putInt(ContentFragment.POSITION, 1);
			contentFragment.setArguments(args);
			
			SherlockFragmentActivity sherlockActivity = getSherlockActivity();
			sherlockActivity.getSupportFragmentManager().beginTransaction()
				.replace(this.getId(), contentFragment)
				.addToBackStack(null)
				.commit();
			
		}
	}

	public static class ContentListAdapter extends ArrayAdapter<String> {

		private final Context context;
		private final String[] value;
		private final HashMap<String, Object> actualValue;

		public ContentListAdapter(Context context, String[] objects, HashMap<String, Object> actualValue) {

			super(context, R.layout.content_list, objects);

			this.context = context;
			this.value = objects;
			this.actualValue = actualValue;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View rowView = null;

			
			
			Object objectValue = actualValue.values().toArray()[position];
			if (objectValue instanceof String) {

				rowView = inflater.inflate(R.layout.content_question_item,
						parent, false);
				TextView questionTextView = (TextView) rowView
						.findViewById(R.id.textView1);

				questionTextView.setText(objectValue.toString());

			}

			else {
				rowView = inflater.inflate(R.layout.content_subitem, parent,
						false);
				TextView subItemTextView = (TextView) rowView
						.findViewById(R.id.subItem);

				subItemTextView.setText(value[position]);
			}

			return rowView;
		}

	}

}
