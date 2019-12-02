package com.sisf.Activity;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.sisf.Adapter.Adapter_FAQ;
import com.sisf.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQ extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new Adapter_FAQ(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
               /* Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
               /* Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
               /* Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();*/
                return false;
            }
        });

    }


    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> Acca_india = new ArrayList<String>();
        Acca_india.add("ACCA being a globally recognized qualification, that offers you exposure and job prospects into the top most designation in any industry provides various career opportunities in India as well as globally. Some of these opportunities are as follows:");
        Acca_india.add("1. Corporate Reporting Preparing high-quality business reports to support stakeholder understanding & decision making.");
        Acca_india.add("2. Leadership and Management Managing resources & leading organizations effectively & ethically, understanding stakeholder needs & priorities.");
        Acca_india.add("3. Strategy and Innovation Assessing & evaluating strategic position and identifying imaginative options to improve performance & position; implementing innovative & cost effective solutions leading to effective change management & business process improvement.");
        Acca_india.add("4. Financial Management Implementing effective investment & financing decisions within the business environment in areas such as investment appraisal, tax & risk management, treasury & working capital management, to ensure value creation.");
        Acca_india.add("5. Sustainable Management Accounting Assessing, evaluating and implementing management accounting and performance management systems for planning, measuring, controlling & monitoring business performance to ensure sustainable value creation.");
        Acca_india.add("6. Taxation Complying with tax regulation and systems, communicating with relevant authorities to establish and ethically manage tax liabilities for individuals and companies, using appropriate tax computation and planning techniques.");
        Acca_india.add("7. Audit and Assurance Providing high quality audits by evaluating information systems and internal controls, gathering evidence and performing procedures to meet the objectives of audit and assurance engagements.");
        Acca_india.add("8. Governance Risk and Ethics Ensuring effective and appropriate governance; to evaluate, monitor & implement appropriate risk identification procedures by designing and implementing effective internal audit & control systems.");
        Acca_india.add("9. Stakeholder Relationship Management Managing stakeholder expectations and needs by aligning the organization to their requirements, engaging s take holder s effectively and communicating relevant information.");
        Acca_india.add("10. Professionalism and Ethics Applying knowledge, sensitivity and judgment to act in accordance with fundamental principles of ethical behaviour & personal ethics.");

        List<String> Acc_pro = new ArrayList<String>();
        Acc_pro.add("ACCAPRO is a unique combination of advanced financial and accounting qualification delivered only at SISF. A programme developed by intense research of the industry and it’s leaders, the curriculum benefits our students to procure a distinguished set of skills that will help them meet & exceed expectations in all future endeavors.");
        Acc_pro.add("The Programme is designed to deliver the core benefits of ACCA with a varied skillset to ensure immediate acceptance of our students in the industry. In ACCAPRO we provide ACCA qualification alongside with certifications in Business Analysis (ECBA by IIBA) as well as a CDTP (Corporate Development Training Programme) developed with the assistance of industry experts decorated with years of experience in the field of Recruitment and Human Resource Management.");

        List<String> i_grad = new ArrayList<String>();
        i_grad.add("ACCA together with OBU (Oxford Brooks University) has developed a degree exclusively available to ACCA students. This program enables the student to acquire not only ACCA qualification but also a degree, which together proves to be more valuable. It provides students a better opportunity, as such advanced combinations are desired by employers in multiple fields.");
        i_grad.add("iGRAD in Finance, introduced by SISF incorporates ACCA qualification with extensive learning that includes ECBA by IIBA and CDTP (Corporate Development Training Program). Moreover, it also delivers the benefits of NCFM by NSE and regular guidance for your international graduation also known as OBU Mentorship, all under the same roof.");

        List<String> why_acca_pro = new ArrayList<String>();
        why_acca_pro.add("ACCA pro is a combination of unique skill sets, that will help our students acquire a strong hand when competing with other candidates. This programme offers a number of add ons that are primarily noticed by professional recruiters or human resource management personnel. The programme focuses not only on your ACCA Qualification but also provides an exposure to the factors that are equally important as soon as you step out of the campus into the global arena.");

        List<String> why_i_grad = new ArrayList<String>();
        why_i_grad.add("An opportunity to procure an international education as well as qualification should never be missed. With the vision of providing overseas education to our fellow students here in Udaipur itself we have introduced the concept of “iGRAD in Finance”. This programme incorporates ACCA qualification with extensive learning that includes ECBA by IIBA and CDTP (Corporate Development Training Program). Moreover, it also delivers the benefits of NCFM by NSE and regular guidance for your international graduation also known as OBU Mentorship, all under the same roof. Such advanced training will ensure your immediate acceptance in this competitive epidemic. There is no reason to feel that iGRAD in Finance is not an appropriate choice for any aspiring finance and accounting professional.");

        List<String> Acc = new ArrayList<String>();
        Acc.add("ACCA, the Association of Chartered Certified Accountants is the world’s largest accounting body offering one of the top most accounting qualifications that is “Chartered Certified Accountant”. It was founded back in the year 1904 and since then has grown with every passing day. Currently ACCA consists of 2,00,000 members with 4,86,000 students in 180 countries, making it the largest financial and accounting organization around the world.");
        Acc.add("With global recognition ACCA is one of the best career choice in the field of finance and accounting.");

        expandableListDetail.put("Scope of ACCA in India", Acca_india);
        expandableListDetail.put("What is ACCA PRO?", Acc_pro);
        expandableListDetail.put("What is ACCA?", Acc);
        expandableListDetail.put("What is iGRAD in Finance?", i_grad);
        expandableListDetail.put("Why ACCA pro?", why_acca_pro);
        expandableListDetail.put("Why iGRAD in Finance?", why_i_grad);
        return expandableListDetail;
    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            expandableListView.setIndicatorBounds(width-GetPixelFromDips(55), width-GetPixelFromDips(5));
        } else {
            expandableListView.setIndicatorBoundsRelative(width-GetPixelFromDips(55), width-GetPixelFromDips(5));
        }
    }
}
